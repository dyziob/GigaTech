package com.gigatech.shop.service;

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

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(Cart cart, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapToOrder(orderDto);
        orderRepository.save(order);
        List<OrderItem> orderItems = OrderMapper.mapToOrderItemList(cart, order);
        orderItemRepository.saveAll(OrderMapper.mapToOrderItemList(cart, order));
        cart.cleanCart();
    }
}
