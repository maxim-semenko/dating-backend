package com.maxsoft.datingbackend.user;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    @EntityGraph(attributePaths = {"roles"})
    @NonNull
    List<UserModel> findAll();

    Optional<UserModel> findByEmail(String email);

}
