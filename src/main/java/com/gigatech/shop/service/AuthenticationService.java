package com.gigatech.shop.service;
// Klasa AuthenticationService odpowiada za operacje związane z autentykacją użytkowników.
import com.gigatech.shop.Repository.RoleRepository;
import com.gigatech.shop.Repository.UserRepository;
import com.gigatech.shop.dto.LoginResponseDTO;
import com.gigatech.shop.model.Role;
import com.gigatech.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    // Repozytorium użytkowników.
    @Autowired
    private UserRepository userRepository;
    // Repozytorium ról.
    @Autowired
    private RoleRepository roleRepository;
    // Kodujący hasła.
    @Autowired
    private PasswordEncoder passwordEncoder;
    // Menadżer autentykacji.
    @Autowired
    private AuthenticationManager authenticationManager;
    // Serwis do obsługi tokenów JWT.
    @Autowired
    private TokenService tokenService;
    // Metoda rejestracji nowego użytkownika.
    public User registerUser(String username, String password){

        // Kodowanie hasła.
        String encodedPassword = passwordEncoder.encode(password);
        // Pobranie roli użytkownika.
        Role userRole = roleRepository.findByAuthority("USER").orElseThrow(() -> new RuntimeException("Rola 'USER' nie istnieje"));

        // Utworzenie zbioru ról użytkownika.
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        // Zapis nowego użytkownika do bazy danych.
        return userRepository.save(new User(username, encodedPassword, authorities));
    }

    // Metoda logowania użytkownika.
    public LoginResponseDTO loginUser(String username, String password){

        try{
            // Autentykacja użytkownika.
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            // Generowanie tokena JWT.
            String token = tokenService.generateJwt(auth);
            // Pobranie informacji o zalogowanym użytkowniku.
            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
            // Obsługa błędu autentykacji.
        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }
}
