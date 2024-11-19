package com.maxsoft.datingbackend.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthRequestDto {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    private String code;
}
