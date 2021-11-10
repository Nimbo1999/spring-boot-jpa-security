package io.github.nimbo1999.rest.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticateRequestDTO {
    @NotEmpty(message = "O nome do usuário é obrigatório")
    private String username;
    @NotEmpty(message = "É obrigatório informar a senha de acesso")
    private String password;
}
