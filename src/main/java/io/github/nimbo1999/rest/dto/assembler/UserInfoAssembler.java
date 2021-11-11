package io.github.nimbo1999.rest.dto.assembler;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.github.nimbo1999.domain.entity.Authority;
import io.github.nimbo1999.domain.entity.User;
import io.github.nimbo1999.rest.dto.UserInfoDTO;

public class UserInfoAssembler implements Function<User, UserInfoDTO> {

    @Override
    public UserInfoDTO apply(User user) {
        List<String> authorities = formatUserAuthorities(user);

        return UserInfoDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .authorities(authorities)
            .build();
    }

    private List<String> formatUserAuthorities(User user) {
        return user.getAuthorities()
            .stream()
            .map(authority -> convert(authority))
            .collect(Collectors.toList());
    }

    private String convert(Authority authority) {
        return authority.getAuthority().name();
    }
    
}
