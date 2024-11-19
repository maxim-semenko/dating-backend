package com.maxsoft.datingbackend.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public Mono<String> register(@Valid @RequestBody AuthRequestDto authRequestDto) {
        return Mono.just(authService.register(authRequestDto.getEmail(), authRequestDto.getPassword()));
    }

    @PostMapping("/login")
    public Mono<String> login(@Valid @RequestBody AuthRequestDto authRequestDto) {
        return Mono.just(authService.login(authRequestDto.getEmail(), authRequestDto.getPassword()));
    }

}
