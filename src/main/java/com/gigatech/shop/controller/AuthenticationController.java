package com.gigatech.shop.controller;

import com.gigatech.shop.dto.LoginResponseDTO;
import com.gigatech.shop.dto.RegistrationDto;
import com.gigatech.shop.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    // Obsługa żądania GET do wyświetlenia formularza rejestracji
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationDto", new RegistrationDto());
        return "register";
    }

    // Obsługa żądania POST do rejestracji użytkownika
    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegistrationDto registrationDto) {
        // Rejestracja użytkownika za pomocą AuthenticationService
        authenticationService.registerUser(registrationDto.getUsername(), registrationDto.getPassword());
        // Przekierowanie do strony logowania po udanej rejestracji
        return "redirect:/auth/login";
    }

    // Obsługa żądania GET do wyświetlenia formularza logowania
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Obsługa żądania POST do logowania użytkownika
    @PostMapping("/login")
    public String loginUser(@ModelAttribute RegistrationDto body, Model model) {
        // Logowanie użytkownika za pomocą AuthenticationService
        LoginResponseDTO loginResponseDTO = authenticationService.loginUser(body.getUsername(), body.getPassword());

        // Sprawdzanie czy logowanie było udane
        if (loginResponseDTO.getUser() != null) {
            // Przekazanie danych użytkownika i tokena do modelu
            model.addAttribute("user", loginResponseDTO.getUser());
            model.addAttribute("jwt", loginResponseDTO.getJwt());
            // Przekierowanie na stronę główną po udanym logowaniu
            return "redirect:/";
        } else {
            // Przekierowanie do strony logowania z komunikatem o błędzie
            return "redirect:/auth/login?error";
        }
    }
}
