package io.github.nimbo1999.rest.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticateRequestDTO {
    @NotEmpty(message = "username it is required")
    private String username;
    @NotEmpty(message = "password it is required")
    private String password;
}
