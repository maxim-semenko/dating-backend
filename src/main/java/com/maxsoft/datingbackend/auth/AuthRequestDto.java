package com.maxsoft.datingbackend.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthRequestDto {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private String code;
}
