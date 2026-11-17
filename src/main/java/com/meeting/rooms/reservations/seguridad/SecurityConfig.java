package com.meeting.rooms.reservations.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactivar CSRF para pruebas en APIs/H2
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())) // Necesario para la consola H2
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() // Permitir acceso a H2
                .requestMatchers("/api/listar/**").permitAll() //Permitir acceso a los listar
                .anyRequest().authenticated() // El resto requiere autenticación
            )
            .httpBasic(Customizer.withDefaults()); // Autenticación básica HTTP

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Codificador de contraseñas recomendado
    }
}
