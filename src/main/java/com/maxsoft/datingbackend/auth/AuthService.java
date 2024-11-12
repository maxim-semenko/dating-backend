package com.maxsoft.datingbackend.auth;

import com.maxsoft.datingbackend.auth.jwt.JwtTokenProvider;
import com.maxsoft.datingbackend.user.UserModel;
import com.maxsoft.datingbackend.user.UserRepository;
import com.maxsoft.datingbackend.user.role.RoleEnum;
import com.maxsoft.datingbackend.user.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
@AllArgsConstructor
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private RoleRepository roleRepository;

    public String register(String email, String password) {
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of());

        user.setRoles(Set.of(roleRepository.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatusCode.valueOf(404), "Role not found!"))));


        userRepository.save(user);

        String token = jwtTokenProvider.generateToken(user.getEmail(), user.getRoles());

        System.out.println(token);

        return token;
    }
}
