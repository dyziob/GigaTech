package com.gigatech.shop.service;
// Klasa OrderService obsługuje operacje związane z zamówieniami.
import com.gigatech.shop.Cart;
import com.gigatech.shop.Repository.order.OrderItemRepository;
import com.gigatech.shop.Repository.order.OrderRepository;
import com.gigatech.shop.dto.OrderDto;
import com.gigatech.shop.mapper.OrderMapper;
import com.gigatech.shop.model.order.Order;
import com.gigatech.shop.model.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    // Koszyk zakupowy.
    private final Cart cart;
    // Repozytorium zamówień.
    private final OrderRepository orderRepository;
    // Repozytorium pozycji zamówienia.
    private final OrderItemRepository orderItemRepository;
    // Wstrzykiwanie zależności.
    @Autowired
    public OrderService(Cart cart, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }
    // Metoda zapisująca nowe zamówienie.
    public void saveOrder(OrderDto orderDto) {
        // Mapowanie obiektu DTO na obiekt zamówienia.
        Order order = OrderMapper.mapToOrder(orderDto);

        // Zapis zamówienia do repozytorium.
        orderRepository.save(order);

        // Mapowanie pozycji zamówienia z koszyka i zapis do repozytorium.
        List<OrderItem> orderItems = OrderMapper.mapToOrderItemList(cart, order);
        orderItemRepository.saveAll(OrderMapper.mapToOrderItemList(cart, order));

        // Wyczyszczenie koszyka po złożeniu zamówienia.
        cart.cleanCart();
    }
}
