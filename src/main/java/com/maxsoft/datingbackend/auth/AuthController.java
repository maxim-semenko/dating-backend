package com.maxsoft.datingbackend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/info")
    public String getStatus() {
        return "Auth API is working";
    }

    @GetMapping("/register")
    public Mono<String> register(@RequestBody RegisterDto registerDto) {
        return Mono.just(authService.register(registerDto.getEmail(), registerDto.getPassword()));
    }

    @GetMapping("/login")
    public String login() {
        return "LOGIN";
    }

}
