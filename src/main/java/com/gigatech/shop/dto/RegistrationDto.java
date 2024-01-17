package com.gigatech.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RegistrationDto {

    private String username;
    private String password;

    public RegistrationDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String toString(){
        return "Registration info: username: " + this.username + " password: " + this.password;
    }
}
