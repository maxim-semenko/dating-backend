package com.maxsoft.datingbackend.auth.jwt;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtAuthenticationFilter implements WebFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public @NotNull Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            String token = extractToken(exchange);

            if (token != null && jwtTokenProvider.validateJwtToken(token)) {
                String username = jwtTokenProvider.getUsernameFromToken(token);
                List<String> roles = jwtTokenProvider.getRolesFromToken(token);

                List<GrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, authorities);

                return chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
            }
        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e.getMessage());

        }
        return chain.filter(exchange);

    }

    private String extractToken(ServerWebExchange exchange) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        return (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
                ? authorizationHeader.substring(7)
                : null;
    }
}
