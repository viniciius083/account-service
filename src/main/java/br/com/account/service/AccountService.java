package br.com.account.service;

import br.com.account.dto.AccountDTO;
import br.com.account.dto.AccountUpsertEvent;
import br.com.account.events.EventPublisher;
import br.com.account.model.Account;
import br.com.account.model.enumeration.AccountStatus;
import br.com.account.repository.AccountRepository;
import br.com.exceptions.exceptions.ConflictException;
import br.com.exceptions.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EventPublisher eventPublisher;

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

        if(status == AccountStatus.APPROVED) {
            eventPublisher.publishAccountUpsertEvent(new AccountUpsertEvent(account));
        }

        return new AccountDTO(accountRepository.save(account));
    }
}
