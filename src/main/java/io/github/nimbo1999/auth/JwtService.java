package io.github.nimbo1999.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-time-hours}")
    private String expirationInHours;
    @Value("${security.jwt.secret}")
    private String secret;

}
