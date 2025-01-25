package br.com.account.dto;

import br.com.account.model.Account;
import br.com.account.model.Address;
import br.com.account.model.enumeration.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID identifier;
    private String cnpj;
    private String companyName;
    private String tradingName;
    private String email;
    private AddressDTO address;
    private String phoneNumber;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private AccountStatus status;


    public AccountDTO(Account account) {
        this.setIdentifier(account.getIdentifier());
        this.setCnpj(account.getCnpj());
        this.setCompanyName(account.getCompanyName());
        this.setTradingName(account.getTradingName());
        this.setEmail(account.getEmail());
        this.setAddress(new AddressDTO(account.getAddress()));
        this.setPhoneNumber(account.getPhoneNumber());
        this.setStatus(account.getStatus());
    }
}
