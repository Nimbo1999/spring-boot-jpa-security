package io.github.nimbo1999.rest.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.nimbo1999.domain.entity.User;
import io.github.nimbo1999.domain.repository.UserRepository;
import io.github.nimbo1999.exceptions.NotFoundException;
import io.github.nimbo1999.rest.dto.UserInfoDTO;
import io.github.nimbo1999.rest.dto.assembler.UserInfoAssembler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository repository;

    @GetMapping
    public UserInfoDTO getUserInfo(Principal principal) {
        User user = repository.findByUsername(principal.getName())
            .orElseThrow(() -> new NotFoundException("User not found"));
        return new UserInfoAssembler().apply(user);
    }

}
