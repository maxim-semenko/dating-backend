package com.maxsoft.datingbackend.profile.gender;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "gender")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenderModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderEnum name;
}
