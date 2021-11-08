package io.github.nimbo1999.rest.dto.assembler;

import java.util.function.Function;

import io.github.nimbo1999.auth.JwtService;
import io.github.nimbo1999.auth.UserDetail;
import io.github.nimbo1999.service.impl.UserServiceImpl;

public class UserDetailAssembler implements Function<String, UserDetail> {

    private final JwtService service;
    private final UserServiceImpl userServiceImpl;

    public UserDetailAssembler(JwtService service, UserServiceImpl userServiceImpl) {
        this.service = service;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public UserDetail apply(String jwt) {
        String username = service.getUsernameFromJwt(jwt);
        try {
            UserDetail userDetail = userServiceImpl.loadUserByUsername(username);
            return userDetail;
        } catch (Exception ex) {
            return null;
        }
        // List<UserAuthority> userAuthorities = getUserAuthorities(service.getAuthoritiesFromJwt(jwt));
    }
}
