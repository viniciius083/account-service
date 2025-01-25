package br.com.account.dto;

import br.com.account.model.Account;
import br.com.account.model.Address;
import lombok.Data;

import java.util.UUID;

@Data
public class AccountUpsertEvent {
    private UUID accountId;
    private String cnpj;
    private String companyName;
    private String tradingName;
    private String email;
    private Address address;
    private String phoneNumber;
    private String status;

    public AccountUpsertEvent(UUID accountId, String cnpj, String companyName, String tradingName, String email,
                              Address address, String phoneNumber, String status) {
        this.accountId = accountId;
        this.cnpj = cnpj;
        this.companyName = companyName;
        this.tradingName = tradingName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public AccountUpsertEvent(Account account) {
    }
}
