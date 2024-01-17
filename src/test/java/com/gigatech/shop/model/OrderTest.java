package com.gigatech.shop.model;

import com.gigatech.shop.model.order.Order;
import com.gigatech.shop.model.order.OrderItem;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {

    @Test
    void testNoArgsConstructor() {
        // Act
        Order order = new Order();

        // Assert
        assertThat(order).isNotNull();
        assertThat(order.getOrderId()).isNull();
        assertThat(order.getFirstName()).isNull();
        assertThat(order.getLastName()).isNull();
        assertThat(order.getAddress()).isNull();
        assertThat(order.getPostCode()).isNull();
        assertThat(order.getCity()).isNull();
        assertThat(order.getCreated()).isNull();
        assertThat(order.getOrderItems()).isNull();
    }

    @Test
    void testAllArgsConstructor() {
        // Arrange
        Long orderId = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Main St";
        String postCode = "12345";
        String city = "Example City";
        LocalDateTime created = LocalDateTime.now();
        List<OrderItem> orderItems = new ArrayList<>();  // Assuming OrderItem has a default constructor

        // Act
        Order order = Order.builder()
                .orderId(orderId)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .postCode(postCode)
                .city(city)
                .created(created)
                .orderItems(orderItems)
                .build();

        // Assert
        assertThat(order).isNotNull();
        assertThat(order.getOrderId()).isEqualTo(orderId);
        assertThat(order.getFirstName()).isEqualTo(firstName);
        assertThat(order.getLastName()).isEqualTo(lastName);
        assertThat(order.getAddress()).isEqualTo(address);
        assertThat(order.getPostCode()).isEqualTo(postCode);
        assertThat(order.getCity()).isEqualTo(city);
        assertThat(order.getCreated()).isEqualTo(created);
        assertThat(order.getOrderItems()).isEqualTo(orderItems);
    }

    @Test
    void testBuilder() {
        // Arrange
        Long orderId = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String address = "123 Main St";
        String postCode = "12345";
        String city = "Example City";
        LocalDateTime created = LocalDateTime.now();
        List<OrderItem> orderItems = new ArrayList<>();  // Assuming OrderItem has a default constructor

        // Act
        Order order = Order.builder()
                .orderId(orderId)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .postCode(postCode)
                .city(city)
                .created(created)
                .orderItems(orderItems)
                .build();

        // Assert
        assertThat(order).isNotNull();
        assertThat(order.getOrderId()).isEqualTo(orderId);
        assertThat(order.getFirstName()).isEqualTo(firstName);
        assertThat(order.getLastName()).isEqualTo(lastName);
        assertThat(order.getAddress()).isEqualTo(address);
        assertThat(order.getPostCode()).isEqualTo(postCode);
        assertThat(order.getCity()).isEqualTo(city);
        assertThat(order.getCreated()).isEqualTo(created);
        assertThat(order.getOrderItems()).isEqualTo(orderItems);
    }
}
