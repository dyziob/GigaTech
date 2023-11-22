package com.gigatech.shop.controller;


import com.gigatech.shop.Repository.ItemRepository;
import com.gigatech.shop.model.Item;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class HomeController {

    private final ItemRepository itemRepository;
    @Autowired
    public HomeController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession httpSession){
        model.addAttribute("items", itemRepository.findAll());
        return "index";
    }
}
