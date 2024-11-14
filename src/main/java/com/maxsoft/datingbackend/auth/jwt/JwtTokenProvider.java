package com.maxsoft.datingbackend.auth.jwt;

import com.maxsoft.datingbackend.user.role.RoleModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@PropertySource("/jwt.properties")
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.token.key}")
    private String secretKey;

    @Value("${jwt.token.expiration}")
    private Long expirationTime;

    public String generateToken(String username, Set<RoleModel> roles) {
        Key key = new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles.stream().map(RoleModel::getName).collect(Collectors.toList()));

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .setClaims(claims)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (JwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        Object roles = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles");

        if (roles instanceof List<?>) {
            return (List<String>) roles;
        }
        throw new IllegalArgumentException("Invalid roles format in token");
    }

}
