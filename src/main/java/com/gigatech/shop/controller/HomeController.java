package com.gigatech.shop.controller;

import com.gigatech.shop.ItemOperation;
import com.gigatech.shop.model.Item;
import com.gigatech.shop.service.CartService;
import com.gigatech.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final CartService cartService;
    private final ItemService itemService;

    @Autowired
    public HomeController(CartService cartService, ItemService itemService) {
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String home(Model model) {
        Item productOfTheDay = cartService.getProductOfTheDay();
        model.addAttribute("productOfTheDay", productOfTheDay);

        model.addAttribute("items", cartService.getAllItems());
        return "index";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId, Model model) {
        Item productOfTheDay = cartService.getProductOfTheDay();
        model.addAttribute("productOfTheDay", productOfTheDay);

        cartService.ItemOperation(itemId, ItemOperation.INCREASE);
        model.addAttribute("items", cartService.getAllItems());
        return "index";
    }

    @GetMapping("/products/{id}")
    public String showProductDetails(@PathVariable long id, Model model) {
        Item product = itemService.findItemById(id);

        if (product != null) {
            model.addAttribute("product", product);
            return "productdetails";
        } else {
            return "error";
        }
    }

    @GetMapping("/category/{category}")
    public String showCategoryItems(@PathVariable String category, Model model) {
        List<Item> categoryItems = cartService.getCategoryItems(category);
        model.addAttribute("items", categoryItems);
        return "index";
    }
}