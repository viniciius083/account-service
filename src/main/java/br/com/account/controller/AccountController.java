package br.com.account.controller;

import br.com.account.dto.AccountDTO;
import br.com.account.model.enumeration.AccountStatus;
import br.com.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }

    @PutMapping("/{accountIdentifier}/status")
    public AccountDTO updateAccountStatus(@PathVariable UUID accountIdentifier, @RequestParam AccountStatus status) {
        return accountService.updateAccountStatus(accountIdentifier, status);
    }
}
