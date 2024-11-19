package com.maxsoft.datingbackend.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maxsoft.datingbackend.user.role.RoleModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"user\"")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @JsonIgnore
    private String password;

    private String ip;

    private String location;

    @CreatedDate
    private Date createdAt;

    private Boolean isAccountNonLocked = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_m2m_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @JsonIgnore
    @Builder.Default
    private Set<RoleModel> roles = new HashSet<>();

}
