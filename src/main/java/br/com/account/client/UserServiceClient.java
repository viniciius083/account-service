package br.com.account.client;

import br.com.account.dto.UserPassword;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient(name = "user-service", url = "http://localhost:8091")
public interface UserServiceClient {

    @PutMapping("/users/password")
    void updatePassword(@RequestHeader UUID accountIdentifier, @RequestBody UserPassword userPassword);
}
