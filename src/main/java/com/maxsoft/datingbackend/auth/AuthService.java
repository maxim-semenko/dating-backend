package com.maxsoft.datingbackend.auth;

import com.maxsoft.datingbackend.auth.jwt.JwtTokenProvider;
import com.maxsoft.datingbackend.user.UserModel;
import com.maxsoft.datingbackend.user.UserRepository;
import com.maxsoft.datingbackend.user.role.RoleEnum;
import com.maxsoft.datingbackend.user.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;


    public String register(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The email is already in use");
        }
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of());

        user.setRoles(Set.of(roleRepository.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found!"))));


        userRepository.save(user);

        return jwtTokenProvider.generateToken(user.getEmail(), user.getId(), user.getRoles());
    }

    public String login(String email, String password) {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User was not found!"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(roleModel -> new SimpleGrantedAuthority(roleModel.getName().name()))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                email, password, authorities);

        ReactiveSecurityContextHolder.withAuthentication(authentication);

        return jwtTokenProvider.generateToken(user.getEmail(), user.getId(), user.getRoles());
    }
}
