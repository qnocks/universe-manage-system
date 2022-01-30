package com.qnocks.universemanagesystem.security.jwt;

import com.qnocks.universemanagesystem.domain.Role;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.token.secret}")
    private String secret;

    @Value("${app.jwt.token.expired}")
    private long expiredMs;

    public String createToken(String username, List<Role> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", getRoleNames(roles));

        Date now = new Date();
        Date expiration = new Date(now.getTime() + expiredMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            return parseAndValidate(token);
        } catch (JwtException e) {
            throw new JwtAuthException("JWT token is expired or invalid");
        }
    }

    private boolean parseAndValidate(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    public String getSubject(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private List<String> getRoleNames(List<Role> roles) {
        List<String> result = new ArrayList<>();
        roles.forEach(role -> result.add(role.getName()));
        return result;
    }
}
