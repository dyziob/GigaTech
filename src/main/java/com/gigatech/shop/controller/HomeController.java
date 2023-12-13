package com.gigatech.shop.controller;


import com.gigatech.shop.Cart;
import com.gigatech.shop.Repository.ItemRepository;
import com.gigatech.shop.model.Item;
import com.gigatech.shop.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class HomeController {

    private final CartService cartService;

    @Autowired
    public HomeController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession httpSession){
        model.addAttribute("items", cartService.getAllItems());
        return "index";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId, Model model) {
        cartService.addItemToCart(itemId);
        model.addAttribute("items", cartService.getAllItems());
        return "index";
    }
}
