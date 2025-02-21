package com.tary.ServiceFlow.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF para facilitar testes via Postman
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Permite acesso a TODOS os endpoints
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // Desativa autenticação básica
                .formLogin(form -> form.disable()); // Desativa login via formulário

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Garante que senhas sejam criptografadas (se precisar)
    }
}
