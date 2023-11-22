package com.gigatech.shop.controller;

import com.gigatech.shop.Repository.ItemRepository;
import com.gigatech.shop.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ItemRepository itemRepository;
    @Autowired
    public AdminController (ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public String adminPage(){
        return "Admin/additem";
    }

    @PostMapping
    private String addItem(Item item) {
        itemRepository.save(item);
        return "redirect:/";
    }
}
