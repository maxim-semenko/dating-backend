package com.maxsoft.datingbackend.config;

import com.maxsoft.datingbackend.profile.gender.GenderEnum;
import com.maxsoft.datingbackend.profile.gender.GenderModel;
import com.maxsoft.datingbackend.profile.gender.GenderRepository;
import com.maxsoft.datingbackend.user.role.RoleEnum;
import com.maxsoft.datingbackend.user.role.RoleModel;
import com.maxsoft.datingbackend.user.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class DatabaseInitializerConfig {

    private RoleRepository roleRepository;
    private GenderRepository genderRepository;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if (roleRepository.count() == 0) {
                roleRepository.save(RoleModel.builder().name(RoleEnum.ROLE_ADMIN).build());
                roleRepository.save(RoleModel.builder().name(RoleEnum.ROLE_MODERATOR).build());
                roleRepository.save(RoleModel.builder().name(RoleEnum.ROLE_USER).build());
                roleRepository.save(RoleModel.builder().name(RoleEnum.ROLE_SUPER_USER).build());
            }
            if (genderRepository.count() == 0) {
                genderRepository.save(GenderModel.builder().name(GenderEnum.MALE).build());
                genderRepository.save(GenderModel.builder().name(GenderEnum.FEMALE).build());
            }
        };
    }
}

