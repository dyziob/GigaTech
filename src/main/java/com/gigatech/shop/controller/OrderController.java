package com.gigatech.shop.controller;

import com.gigatech.shop.ItemOperation;
import com.gigatech.shop.dto.OrderDto;
import com.gigatech.shop.service.CartService;
import com.gigatech.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final CartService cartService;
    private final OrderService orderService;
    @Autowired
    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    // Obsługa żądania GET do wyświetlenia strony koszyka
    @GetMapping("/cart")
    public String cartPage(){
        return "cartView";
    }

    // Obsługa żądania GET do zwiększenia ilości przedmiotu w koszyku
    @GetMapping("/increase/{itemId}")
    public String IncreaseItem(@PathVariable("itemId") Long itemId) {
        cartService.ItemOperation(itemId, ItemOperation.INCREASE);
        return "cartView";
    }

    // Obsługa żądania GET do zmniejszenia ilości przedmiotu w koszyku
    @GetMapping("/decrease/{itemId}")
    public String DecreaseItem(@PathVariable("itemId") Long itemId) {
        cartService.ItemOperation(itemId, ItemOperation.DECREASE);
        return "cartView";
    }

    // Obsługa żądania GET do usunięcia przedmiotu z koszyka
    @GetMapping("/remove/{itemId}")
    public String removeItemsFromCart(@PathVariable("itemId") Long itemId){
        cartService.ItemOperation(itemId, ItemOperation.REMOVE);
        return "cartView";
    }

    // Obsługa żądania GET do wyświetlenia podsumowania zamówienia
    @GetMapping("/summary")
    public String showSummary() {
        return "summary";
    }

    // Obsługa żądania POST do zapisu zamówienia
    @PostMapping("/saveorder")
    public String saveOrder(OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return "redirect:/";
    }
}
