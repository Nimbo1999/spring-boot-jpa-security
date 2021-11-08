package io.github.nimbo1999.auth;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.nimbo1999.domain.entity.User;
import io.github.nimbo1999.utils.InstantUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.JwtMap;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-time-hours}")
    private String expirationInHours;
    @Value("${security.jwt.secret}")
    private String secret;

    public String generateJwt(User user) {
        JwtMap claimMap = generateClaims(user);

        String jwt = Jwts.builder()
            .setClaims(claimMap)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();

        return jwt;
    }

    private JwtMap generateClaims(User user) {
        Long expirationConverted = 60 * 60 * Long.valueOf(expirationInHours);
        Instant now = InstantUtils.instantNow();
        Long epochNow = now.getEpochSecond();
        Long expirationDate = now.plusSeconds(expirationConverted).getEpochSecond();

        JwtMap claimMap = new JwtMap();
        claimMap.put(Claims.SUBJECT, user.getUsername());
        claimMap.put(Claims.EXPIRATION, expirationDate);
        claimMap.put(Claims.ISSUED_AT, epochNow);
        return claimMap;
    }

    public String getUsernameFromJwt(String token) {
        try {
            return getJWTPayload(token).getSubject();
        } catch (Exception ex) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> getAuthoritiesFromJwt(String token) {
        try {
            return (List<String>) getJWTPayload(token).get("authorities");
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean isValidToken(String token) {
        try {
            Instant expirationDate = getExpirationInstantFromJwt(token);
            return expirationDate.isAfter(InstantUtils.instantNow());
        } catch (Exception ex) {
            return false;
        }
    }

    public Instant getExpirationInstantFromJwt(String token) {
        try {
            Claims claims = getJWTPayload(token);
            return claims.getExpiration().toInstant();
        } catch (Exception ex) {
            return null;
        }
    }

    private Claims getJWTPayload(String jwtToken) throws ExpiredJwtException {
        return getJWTClaims(jwtToken).getBody();
    }

    private Jws<Claims> getJWTClaims(String jwtToken) throws ExpiredJwtException {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(jwtToken);
    }

}
