package io.github.nimbo1999.configs;

import java.util.Arrays;

import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.nimbo1999.auth.JwtAuthFilter;
import io.github.nimbo1999.auth.JwtService;
import io.github.nimbo1999.domain.enums.AuthorityType;
import io.github.nimbo1999.service.impl.UserServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, userService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/customer/**")
                    .hasAnyAuthority(AuthorityType.ALL.name(), AuthorityType.READ.name())
                .antMatchers("/customer/**")
                    .hasAnyAuthority(AuthorityType.ALL.name())
                .antMatchers(HttpMethod.POST, "/auth/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(corsFilter());
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration()
            .applyPermitDefaultValues();

        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Arrays.asList("http://localhost:3000"));
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.HEAD);
        config.addAllowedMethod(HttpMethod.OPTIONS);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.PUT);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
