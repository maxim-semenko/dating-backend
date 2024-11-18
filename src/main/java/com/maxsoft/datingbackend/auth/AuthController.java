package com.maxsoft.datingbackend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/register")
    public Mono<String> register(@RequestBody AuthRequestDto authRequestDto) {
        return Mono.just(authService.register(authRequestDto.getEmail(), authRequestDto.getPassword()));
    }

    @PostMapping("/login")
    public Mono<String> login(@RequestBody AuthRequestDto authRequestDto) {
        return Mono.just(authService.login(authRequestDto.getEmail(), authRequestDto.getPassword()));
    }

}
