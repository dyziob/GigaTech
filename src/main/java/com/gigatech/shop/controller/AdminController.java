package com.gigatech.shop.controller;

import com.gigatech.shop.Repository.ItemRepository;
import com.gigatech.shop.Repository.order.OrderRepository;
import com.gigatech.shop.model.Item;
import com.gigatech.shop.model.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public AdminController(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String adminPage(Model model){
        List<Item> existingItems = itemRepository.findAll();
        model.addAttribute("items", existingItems);
        return "Admin/additem";
    }

    @PostMapping
    private String addItem(Item item) {
        itemRepository.save(item);
        return "redirect:/";
    }

    @PostMapping("/deleteItem/{itemId}")
    public String deleteItem(@PathVariable Long itemId) {
        itemRepository.deleteById(itemId);
        return "redirect:/admin";
    }

    @GetMapping("/showorders")
    @ResponseBody
    public List<Order> showOrders() {
        return orderRepository.findAll();
    }
}
