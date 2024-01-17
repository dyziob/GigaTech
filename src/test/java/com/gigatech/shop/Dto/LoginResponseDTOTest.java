package com.gigatech.shop.Dto;

import com.gigatech.shop.dto.LoginResponseDTO;
import com.gigatech.shop.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LoginResponseDTOTest {

    @Test
    void testNoArgsConstructor() {
        // Act
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        // Assert
        assertNull(loginResponseDTO.getUser());
        assertNull(loginResponseDTO.getJwt());
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        User user = new User();
        String jwt = "sampleJwt";

        // Act
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(user, jwt);

        // Assert
        assertEquals(user, loginResponseDTO.getUser());
        assertEquals(jwt, loginResponseDTO.getJwt());
    }

    @Test
    void testSetterGetter() {
        // Arrange
        User user = new User();
        String jwt = "sampleJwt";

        // Act
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser(user);
        loginResponseDTO.setJwt(jwt);

        // Assert
        assertEquals(user, loginResponseDTO.getUser());
        assertEquals(jwt, loginResponseDTO.getJwt());
    }
}
