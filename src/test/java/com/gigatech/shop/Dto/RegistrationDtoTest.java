package com.gigatech.shop.Dto;

import com.gigatech.shop.dto.RegistrationDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RegistrationDtoTest {

    @Test
    void testNoArgsConstructor() {
        // Act
        RegistrationDto registrationDto = new RegistrationDto();

        // Assert
        assertThat(registrationDto).isNotNull();
        assertThat(registrationDto.getUsername()).isNull();
        assertThat(registrationDto.getPassword()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        String username = "john.doe";
        String password = "secret123";

        // Act
        RegistrationDto registrationDto = new RegistrationDto(username, password);

        // Assert
        assertThat(registrationDto).isNotNull();
        assertThat(registrationDto.getUsername()).isEqualTo(username);
        assertThat(registrationDto.getPassword()).isEqualTo(password);
    }

    @Test
    void testToString() {
        // Arrange
        String username = "john.doe";
        String password = "secret123";
        RegistrationDto registrationDto = new RegistrationDto(username, password);

        // Act
        String toStringResult = registrationDto.toString();

        // Assert
        assertThat(toStringResult).contains(username);
        assertThat(toStringResult).contains(password);
    }
}

