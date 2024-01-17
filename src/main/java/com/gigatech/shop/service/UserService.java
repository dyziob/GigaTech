package com.gigatech.shop.service;
// Klasa UserService pełni rolę serwisu obsługującego operacje związane z użytkownikami.
import com.gigatech.shop.Repository.UserRepository;
import com.gigatech.shop.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    // Wstrzykiwanie zależności - enkoder hasła.
    @Autowired
    private PasswordEncoder encoder;
    // Wstrzykiwanie zależności - repozytorium użytkowników.
    @Autowired
    private UserRepository userRepository;
    // Implementacja metody z interfejsu UserDetailsService.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Logowanie informacji o próbie uzyskania szczegółów użytkownika.
        System.out.println("In the user details service");
        // Pobranie użytkownika z repozytorium na podstawie nazwy użytkownika.
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono użytkownika"));

    }
}
