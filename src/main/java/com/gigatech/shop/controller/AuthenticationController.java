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

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationDto", new RegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegistrationDto registrationDto) {
        authenticationService.registerUser(registrationDto.getUsername(), registrationDto.getPassword());
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute RegistrationDto body, Model model) {
        LoginResponseDTO loginResponseDTO = authenticationService.loginUser(body.getUsername(), body.getPassword());

        if (loginResponseDTO.getUser() != null) {
            model.addAttribute("user", loginResponseDTO.getUser());
            model.addAttribute("jwt", loginResponseDTO.getJwt());
            return "redirect:/";
        } else {
            return "redirect:/auth/login?error";
        }
    }
}
