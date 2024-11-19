package com.maxsoft.datingbackend.auth.jwt;

import com.maxsoft.datingbackend.user.UserModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    @Getter
    private final UUID id;
    private final String email;
    private final Boolean isAccountNonLocked;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(UUID id,
                           String email,
                           Boolean isAccountNonLocked,
                           String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.isAccountNonLocked = isAccountNonLocked;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(UserModel user) {
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getIsAccountNonLocked(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
