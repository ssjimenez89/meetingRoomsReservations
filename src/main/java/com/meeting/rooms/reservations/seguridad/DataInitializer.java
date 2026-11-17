package com.meeting.rooms.reservations.seguridad;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.meeting.rooms.reservations.entity.User;
import com.meeting.rooms.reservations.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin")); // Contraseña encriptada
            user.setRole("USER");
            userRepository.save(user);
        };
    }
}