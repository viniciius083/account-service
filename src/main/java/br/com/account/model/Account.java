package br.com.account.model;

import br.com.account.dto.AccountDTO;
import br.com.account.model.enumeration.AccountStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "tb_account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false, columnDefinition = "BINARY(16)")
    private UUID identifier;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String tradingName;

    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.PENDING;

    public Account(AccountDTO accountDTO) {
        this.setCnpj(accountDTO.getCnpj());
        this.setCompanyName(accountDTO.getCompanyName());
        this.setTradingName(accountDTO.getTradingName());
        this.setEmail(accountDTO.getEmail());
        this.setAddress(new Address(accountDTO.getAddress()));
        this.setPhoneNumber(accountDTO.getPhoneNumber());
    }
}
