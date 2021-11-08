package io.github.nimbo1999.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticateResponseDTO {
    @Getter
    private String accessToken;
    @Getter
    private String tokenType;
    @Getter
    private Long expiresIn;
}
