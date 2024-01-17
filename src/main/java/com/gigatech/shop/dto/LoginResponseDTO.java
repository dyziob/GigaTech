package com.gigatech.shop.dto;

import com.gigatech.shop.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginResponseDTO {

    private User user;
    private String jwt;

    public LoginResponseDTO(User user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }
}
