package com.maxsoft.datingbackend.user.role;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class RoleModel {

    @Id
    private UUID id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleEnum name;
}
