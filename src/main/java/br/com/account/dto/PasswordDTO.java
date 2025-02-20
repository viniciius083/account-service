package br.com.account.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {

    @NotBlank(message = "Identificador da conta obrigatório.")
    private UUID accountIdentifier;

    @NotBlank(message = "Senha obrigatória.")
    private String password;

    @NotBlank(message = "Confirmação de senha obrigatória.")
    private String confirmPassword;
}
