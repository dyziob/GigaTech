package com.gigatech.shop.controller;

import com.gigatech.shop.ItemOperation;
import com.gigatech.shop.dto.OrderDto;
import com.gigatech.shop.service.CartService;
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

    @Autowired
    public OrderController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String cartPage(){
        return "cartView";
    }

    @GetMapping("/increase/{itemId}")
    public String IncreaseItem(@PathVariable("itemId") Long itemId) {
        cartService.ItemOperation(itemId, ItemOperation.INCREASE);
        return "cartView";
    }

    @GetMapping("/decrease/{itemId}")
    public String DecreaseItem(@PathVariable("itemId") Long itemId) {
        cartService.ItemOperation(itemId, ItemOperation.DECREASE);
        return "cartView";
    }

    @GetMapping("/remove/{itemId}")
    public String removeItemsFromCart(@PathVariable("itemId") Long itemId){
        cartService.ItemOperation(itemId, ItemOperation.REMOVE);
        return "cartView";
    }

    @GetMapping("/summary")
    public String showSummary() {
        return "summary";
    }

    @PostMapping("/saveorder")
    public String saveOrder(OrderDto orderDto) {
        return "redirect:/";
    }
}
