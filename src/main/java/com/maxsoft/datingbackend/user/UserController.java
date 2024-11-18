package com.maxsoft.datingbackend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public String getStatus() {
//        return "Users API is working!";
        return "Hello from API.\nVLAD PIDORAS :)";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') and #id == authentication.principal.id")
    public Mono<UserModel> getById(@PathVariable UUID id) {
        System.out.println(id);
        return Mono.just(userService.getById(id));
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER')")
    public Flux<UserModel> findAll() {
        return Flux.fromIterable(userService.findAll());
    }

    @GetMapping("/security/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<String> getAdmin() {
        return Mono.just("ADMIN!");
    }


}
