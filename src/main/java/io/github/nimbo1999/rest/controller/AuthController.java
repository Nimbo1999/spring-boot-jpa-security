package io.github.nimbo1999.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.nimbo1999.auth.JwtService;
import io.github.nimbo1999.domain.entity.User;
import io.github.nimbo1999.exceptions.PasswordIncorrectException;
import io.github.nimbo1999.rest.dto.AuthenticateRequestDTO;
import io.github.nimbo1999.rest.dto.AuthenticateResponseDTO;
import io.github.nimbo1999.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserServiceImpl userServiceImpl;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public AuthenticateResponseDTO authenticateUser(@RequestBody @Valid AuthenticateRequestDTO credentials) {
        User user = new User();
        user.setUsername(credentials.getUsername());
        user.setPassword(credentials.getPassword());

        try {
            String jwt = userServiceImpl.authenticate(user);
            Long expirationTime = jwtService.getExpirationInstantFromJwt(jwt).getEpochSecond();

            return AuthenticateResponseDTO.builder()
                .accessToken(jwt)
                .tokenType("Bearer")
                .expiresIn(expirationTime)
                .build();
        } catch (UsernameNotFoundException | PasswordIncorrectException ex) {
            throw new AuthorizationServiceException("Username or Password incorrect");
        }
    }
}
