package com.maxsoft.datingbackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public String getStatus() {
        return "Users API is working!";
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public Flux<UserModel> findAll() {
        return userService.findAll();
    }

    @GetMapping("/security/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<String> getAdmin() {
        return Mono.just("ADMIN!");
    }

    @GetMapping("/permit")
    @PreAuthorize("permitAll()")
    public Mono<String> getPermit() {
        return Mono.just("permit all!");
    }

}
