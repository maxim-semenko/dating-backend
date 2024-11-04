package com.maxsoft.datingbackend.profile;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "profile")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {

    @Id
    private UUID id;

    private String name;

    private String description;

    private Short age;

}
