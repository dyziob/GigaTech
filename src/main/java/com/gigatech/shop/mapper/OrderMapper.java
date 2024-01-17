package com.gigatech.shop.mapper;

import com.gigatech.shop.Cart;
import com.gigatech.shop.CartItem;
import com.gigatech.shop.dto.OrderDto;
import com.gigatech.shop.model.order.Order;
import com.gigatech.shop.model.order.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    // Metoda mapująca obiekt OrderDto na obiekt Order
    public static Order mapToOrder(OrderDto orderDto) {
        return Order.builder()
                .firstName(orderDto.getFirstName())
                .lastName(orderDto.getLastName())
                .address(orderDto.getAddress())
                .postCode(orderDto.getPostCode())
                .city(orderDto.getCity())
                .created(LocalDateTime.now())
                .build();
    }

    // Metoda mapująca elementy koszyka na listę OrderItem związaną z danym zamówieniem
    public static List<OrderItem> mapToOrderItemList(Cart cart, Order order){
        List<OrderItem> orderItems = new ArrayList<>();
        // Iteracja po elementach koszyka
        for (CartItem ci : cart.getCartItems()) {
            // Tworzenie nowego obiektu OrderItem i dodawanie do listy
            orderItems.add(new OrderItem(order.getOrderId(), ci.getItem().getId(), ci.getCounter()));
        }
        return orderItems;
    }
}
