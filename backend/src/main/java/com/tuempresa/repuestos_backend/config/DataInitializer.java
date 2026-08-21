package com.tuempresa.repuestos_backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.tuempresa.repuestos_backend.domain.AppUser;
import com.tuempresa.repuestos_backend.domain.UserRole;
import com.tuempresa.repuestos_backend.repository.UserRepository;

@Configuration
public class DataInitializer {
  @Bean CommandLineRunner seedUsers(UserRepository users, PasswordEncoder encoder,
      @Value("${app.admin-email}") String email, @Value("${app.admin-password}") String password) {
    return args -> {
      if (users.findByEmail(email).isEmpty()) {
        users.save(new AppUser(email, encoder.encode(password), UserRole.ADMIN, true));
      }
    };
  }
}