package com.example.carsharingservice.config;

import com.example.carsharingservice.model.User;
import com.example.carsharingservice.model.User.Role;
import com.example.carsharingservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void inject() {
        Optional<User> byEmailVlad = userRepository.findByEmail("manager@com.ua");
        if (byEmailVlad.isEmpty()) {
            User manager1 = User.builder()
                    .email("manager@com.ua")
                    .password(passwordEncoder.encode("1234"))
                    .firstName("Vlad")
                    .lastName("Docker")
                    .role(Role.MANAGER)
                    .build();
            userRepository.save(manager1);

            Optional<User> byEmailPavlo = userRepository.findByEmail("manager2@com.ua");
            if (byEmailPavlo.isEmpty()) {
                User manager2 = User.builder()
                        .email("manager2@com.ua")
                        .password(passwordEncoder.encode("5678"))
                        .firstName("Pavlo")
                        .lastName("Swagger")
                        .role(Role.MANAGER)
                        .build();
                userRepository.save(manager2);
            }
        }
    }
}
