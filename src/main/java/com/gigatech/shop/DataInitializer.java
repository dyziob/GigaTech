package com.gigatech.shop;
// Klasa DataInitializer implementuje interfejs CommandLineRunner i jest oznaczona adnotacją @Component.
// Pełni rolę inicjalizatora danych dla aplikacji, tworząc przykładowego użytkownika i role.
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gigatech.shop.model.Role;
import com.gigatech.shop.model.User;
import com.gigatech.shop.Repository.RoleRepository;
import com.gigatech.shop.Repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    // Repozytorium do operacji na encjach User.
    private final UserRepository userRepository;
    // Repozytorium do operacji na encjach Role.
    private final RoleRepository roleRepository;
    // Encoder hasła, używany do zakodowania hasła użytkownika.
    private final PasswordEncoder passwordEncoder;
    // Konstruktor klasy, wstrzykujący zależności (userRepository, roleRepository, passwordEncoder).
    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // Metoda z interfejsu CommandLineRunner, uruchamiana po starcie aplikacji.
    @Override
    public void run(String... args) throws Exception {
        // Inicjalizacja ról.
        initializeRoles();
        // Inicjalizacja przykładowego użytkownika administratora.
        initializeAdminUser();
    }
    // Metoda inicjalizująca role w bazie danych, jeśli nie istnieją.
    private void initializeRoles() {
        if (!roleRepository.findByAuthority("ADMIN").isPresent()) {
            roleRepository.save(new Role("ADMIN"));
        }
        if (!roleRepository.findByAuthority("USER").isPresent()) {
            roleRepository.save(new Role("USER"));
        }
    }
    // Metoda inicjalizująca przykładowego użytkownika administratora w bazie danych, jeśli nie istnieje.
    private void initializeAdminUser() {
        if (!userRepository.findByUsername("admin").isPresent()) {
            // Pobranie roli 'ADMIN' z bazy danych lub zgłoszenie wyjątku, jeśli nie istnieje.
            Role adminRole = roleRepository.findByAuthority("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Rola 'ADMIN' nie istnieje"));
            // Utworzenie zbioru ról i dodanie roli 'ADMIN'.
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            // Utworzenie użytkownika 'admin' z zakodowanym hasłem i przypisanymi rolami.
            User adminUser = new User("admin", passwordEncoder.encode("admin123"), roles);
            // Zapisanie użytkownika w bazie danych.
            userRepository.save(adminUser);
        }
    }
}
