package io.github.nimbo1999.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.nimbo1999.auth.JwtService;
import io.github.nimbo1999.auth.UserAuthority;
import io.github.nimbo1999.auth.UserDetail;
import io.github.nimbo1999.domain.entity.User;
import io.github.nimbo1999.domain.repository.UserRepository;
import io.github.nimbo1999.exceptions.PasswordIncorrectException;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return UserDetail.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .userAuthorities(getUserAuthorities(user))
            .build();
    }

    private List<UserAuthority> getUserAuthorities(User user) {
        return user.getAuthorities()
            .stream()
            .map(authority -> new UserAuthority(authority.getAuthority().name()))
            .collect(Collectors.toList());
    }

    public String authenticate(User user) {
        UserDetails userDetails = loadUserByUsername(user.getUsername());

        boolean passwordIsCorrect = passwordEncoder
            .matches(user.getPassword(), userDetails.getPassword());

        if (passwordIsCorrect) {
            User persistedUser = userRepository
                .findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return jwtService.generateJwt(persistedUser);
        }

        throw new PasswordIncorrectException("Password incorrect!");
    }
    
}
