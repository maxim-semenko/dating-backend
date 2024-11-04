package com.maxsoft.datingbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth  // Устанавливаем лямбду для настройки прав доступа
                        .anyRequest().permitAll()               // Все остальные запросы требуют аутентификации
                )
                .httpBasic(withDefaults())  // Настраиваем HTTP Basic аутентификацию через лямбду
                .csrf(AbstractHttpConfigurer::disable);  // Отключаем CSRF (если не требуется)
        // Отключаем CSRF (если не требуется)
        return http.build();
    }

}
