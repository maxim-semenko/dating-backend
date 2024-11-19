package com.maxsoft.datingbackend.profile.alcohol;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "alcohol")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlcoholModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    @NotNull
    private AlcoholEnum name;
}
