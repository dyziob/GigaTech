package com.gigatech.shop.controller;

import com.gigatech.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        cartService.addItemToCart(itemId);
        return "cartView";
    }

    @GetMapping("/decrease/{itemId}")
    public String DecreaseItem(@PathVariable("itemId") Long itemId) {
        cartService.decreaseItemToCart(itemId);
        return "cartView";
    }

    @GetMapping("/remove/{itemId}")
    public String removeItemsFromCart(@PathVariable("itemId") Long itemId){
        cartService.removeItem(itemId);
        return "cartView";
    }
}
