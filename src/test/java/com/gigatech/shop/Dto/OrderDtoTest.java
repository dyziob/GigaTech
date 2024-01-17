package com.gigatech.shop.Dto;

import com.gigatech.shop.dto.OrderDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderDtoTest {

    @Test
    void testNoArgsConstructor() {
        // Act
        OrderDto orderDto = new OrderDto();

        // Assert
        assertThat(orderDto).isNotNull();
        assertThat(orderDto.getFirstName()).isNull();
        assertThat(orderDto.getLastName()).isNull();
        assertThat(orderDto.getAddress()).isNull();
        assertThat(orderDto.getPostCode()).isNull();
        assertThat(orderDto.getCity()).isNull();
    }

    @Test
    void testSetterGetter() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Main St";
        String postCode = "12345";
        String city = "Example City";

        // Act
        OrderDto orderDto = new OrderDto();
        orderDto.setFirstName(firstName);
        orderDto.setLastName(lastName);
        orderDto.setAddress(address);
        orderDto.setPostCode(postCode);
        orderDto.setCity(city);

        // Assert
        assertThat(orderDto.getFirstName()).isEqualTo(firstName);
        assertThat(orderDto.getLastName()).isEqualTo(lastName);
        assertThat(orderDto.getAddress()).isEqualTo(address);
        assertThat(orderDto.getPostCode()).isEqualTo(postCode);
        assertThat(orderDto.getCity()).isEqualTo(city);
    }
}
