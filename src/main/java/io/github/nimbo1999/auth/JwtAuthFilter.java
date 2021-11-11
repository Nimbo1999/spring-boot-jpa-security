package io.github.nimbo1999.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.nimbo1999.rest.dto.assembler.UserDetailAssembler;
import io.github.nimbo1999.service.impl.UserServiceImpl;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserServiceImpl userServiceImpl;

    public JwtAuthFilter(JwtService jwtService, UserServiceImpl userServiceImpl) {
        this.jwtService = jwtService;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException
    {
        String authorizationHeader = request.getHeader("Authorization");

        if (hasAuthorizationHeader(authorizationHeader)) {
            String[] splitedAuthorizationHeader = authorizationHeader.split(" ");
            String jwtToken = splitedAuthorizationHeader[1];

            if (jwtService.isValidToken(jwtToken)) {
                UserDetail userDetail = getUserDetailsFromJwt(jwtToken);
                setSecurityContextAuthorization(userDetail);
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean hasAuthorizationHeader(String authorizationHeader) {
        return authorizationHeader != null &&
            authorizationHeader.startsWith("Bearer") &&
            authorizationHeader.split(" ").length == 2;
    }

    private UserDetail getUserDetailsFromJwt(String jwtToken) {
        return new UserDetailAssembler(jwtService, userServiceImpl).apply(jwtToken);
    }

    private void setSecurityContextAuthorization(UserDetail userDetail) {
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    
}
