package br.com.account.repository;


import br.com.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByCnpj(String cnpj);

    Optional<Account> findByEmail(String email);
}
