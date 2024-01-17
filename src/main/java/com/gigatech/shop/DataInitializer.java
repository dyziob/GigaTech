package com.gigatech.shop;

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

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
        initializeAdminUser();
    }

    private void initializeRoles() {
        if (!roleRepository.findByAuthority("ADMIN").isPresent()) {
            roleRepository.save(new Role("ADMIN"));
        }
        if (!roleRepository.findByAuthority("USER").isPresent()) {
            roleRepository.save(new Role("USER"));
        }
    }

    private void initializeAdminUser() {
        if (!userRepository.findByUsername("admin").isPresent()) {
            Role adminRole = roleRepository.findByAuthority("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Rola 'ADMIN' nie istnieje"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User adminUser = new User("admin", passwordEncoder.encode("admin123"), roles);
            userRepository.save(adminUser);
        }
    }
}
