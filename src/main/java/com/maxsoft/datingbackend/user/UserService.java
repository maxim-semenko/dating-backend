package com.maxsoft.datingbackend.user;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public Flux<UserModel> findAll() {
        return Flux.fromIterable(userRepository.findAll());
    }
}
