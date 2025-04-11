package br.com.account.service;

//import br.com.account.client.UserServiceClient;

import br.com.account.client.UserServiceClient;
import br.com.account.dto.AccountDTO;
import br.com.account.dto.PasswordDTO;
import br.com.account.dto.UserPassword;
import br.com.account.messaging.QueuesProducer;
import br.com.account.model.Account;
import br.com.account.model.enumeration.AccountStatus;
import br.com.account.repository.AccountRepository;
import br.com.exceptions.exceptions.ConflictException;
import br.com.exceptions.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final QueuesProducer queuesProducer;

    @Autowired
    private final UserServiceClient userServiceClient;

    public AccountDTO createAccount(AccountDTO accountDTO) {
        if (accountRepository.findByCnpj(accountDTO.getCnpj()).isPresent()) {
            throw new ConflictException("Conta já existe para o CNPJ: " + accountDTO.getCnpj());
        }

        if (accountRepository.findByEmail(accountDTO.getEmail()).isPresent()) {
            throw new ConflictException("Conta já existe para o e-mail: " + accountDTO.getEmail());
        }

        return new AccountDTO(accountRepository.save(new Account(accountDTO)));
    }

    public AccountDTO  updateAccountStatus(UUID accountId, AccountStatus status) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Conta não encontrada: " + accountId));
        account.setStatus(status);

        AccountDTO accountDTO = new AccountDTO(accountRepository.save(account));
        queuesProducer.sendAccountUpsertEvent(accountDTO);

        return accountDTO;
    }

    public AccountDTO createPassword(PasswordDTO passwordDTO) {
        Account account = accountRepository.findById(passwordDTO.getAccountIdentifier()).orElseThrow(
                () -> new NotFoundException("Conta não encontrada: " + passwordDTO.getAccountIdentifier()));

        accountValidations(passwordDTO, account);
        account.setStatus(AccountStatus.ACTIVE);
        userServiceClient.updatePassword(account.getIdentifier(), new UserPassword(passwordDTO.getPassword()));

        return  new AccountDTO(accountRepository.save(account));
    }

    private static void accountValidations(PasswordDTO passwordDTO, Account account) {
        if(account.getStatus() != AccountStatus.APPROVED) {
            throw new ConflictException("Conta ainda não foi aprovada ou já está ativa.");
        }

        if(!passwordDTO.getPassword().equals(passwordDTO.getConfirmPassword())) {
            throw new ConflictException("Senhas diferentes.");
        }
    }

    public void resendQueue(UUID accountIdentifier) {
        Account account = accountRepository.findById(accountIdentifier).orElseThrow(
                () -> new NotFoundException("Conta não encontrada: " + accountIdentifier));
        queuesProducer.sendAccountUpsertEvent(new AccountDTO(account));
    }
}
