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

    // Wstrzykiwanie zależności za pomocą konstruktora
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public AdminController(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    // Obsługa żądań GET na ścieżce "/admin"
    @GetMapping
    public String adminPage(Model model){
        // Pobieranie wszystkich przedmiotów z ItemRepository
        List<Item> existingItems = itemRepository.findAll();
        // Dodawanie listy przedmiotów do modelu w celu przekazania do widoku
        model.addAttribute("items", existingItems);
        // Zwracanie nazwy widoku "Admin/additem"
        return "Admin/additem";
    }

    // Obsługa żądań POST do dodania nowego przedmiotu
    @PostMapping
    private String addItem(Item item) {
        // Zapisywanie nowego przedmiotu za pomocą ItemRepository
        itemRepository.save(item);
        // Przekierowywanie do strony głównej ("/") po dodaniu przedmiotu
        return "redirect:/";
    }

    // Obsługa żądań POST do usunięcia przedmiotu o danym ID
    @PostMapping("/deleteItem/{itemId}")
    public String deleteItem(@PathVariable Long itemId) {
        // Usuwanie przedmiotu o określonym ID za pomocą ItemRepository
        itemRepository.deleteById(itemId);
        // Przekierowywanie z powrotem do strony admina po usunięciu przedmiotu
        return "redirect:/admin";
    }

    // Obsługa żądań GET na ścieżce "/admin/showorders"
    @GetMapping("/showorders")
    @ResponseBody
    public List<Order> showOrders() {
        // Pobieranie wszystkich zamówień z OrderRepository i zwracanie jako listy
        return orderRepository.findAll();
    }
}
