package io.github.nimbo1999.auth;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {

    private final String authority;

    public UserAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
    
}
