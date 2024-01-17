package com.gigatech.shop.controller;

import com.gigatech.shop.ItemOperation;
import com.gigatech.shop.model.Comment;
import com.gigatech.shop.model.Item;
import com.gigatech.shop.service.CartService;
import com.gigatech.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.PublicKey;
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

    // Obsługa żądania GET na stronę główną ("/")
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal JwtAuthenticationToken authentication) {
        // Pobieranie produktu dnia z koszyka
        Item productOfTheDay = cartService.getProductOfTheDay();
        // Dodawanie listy wszystkich przedmiotów do modelu w celu przekazania do widoku
        model.addAttribute("productOfTheDay", productOfTheDay);

        model.addAttribute("items", cartService.getAllItems());

        if (authentication != null) {
            Jwt jwt = authentication.getToken();
            if (jwt != null) {
                String tokenValue = jwt.getTokenValue();
                model.addAttribute("token", tokenValue);
            }
        }

        return "index";
    }

    @GetMapping("/contact")
    public String kontakt(){
        return "kontakt";
    }

    // Obsługa żądania GET do dodania przedmiotu do koszyka
    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId, Model model) {
        // Pobieranie produktu dnia z koszyka
        Item productOfTheDay = cartService.getProductOfTheDay();
        model.addAttribute("productOfTheDay", productOfTheDay);

        // Wykonywanie operacji zwiększenia ilości przedmiotu w koszyku
        cartService.ItemOperation(itemId, ItemOperation.INCREASE);
        // Aktualizowanie listy przedmiotów w modelu
        model.addAttribute("items", cartService.getAllItems());
        return "index";
    }

    // Obsługa żądania GET do wyświetlenia szczegółów produktu
    @GetMapping("/products/{id}")
    public String showProductDetails(@PathVariable long id, Model model) {
        // Pobieranie informacji o przedmiocie o określonym ID
        Item product = itemService.findItemById(id);

        // Sprawdzanie czy przedmiot istnieje
        if (product != null) {
            model.addAttribute("product", product);
            return "productdetails";
        } else {
            return "Errors/error";
        }
    }

    // Obsługa żądania POST do dodania komentarza do produktu
    @PostMapping("/addComment")
    public String addComment(@RequestParam long itemId, @RequestParam String username, @RequestParam String commentText, @RequestParam int rating) {
        // Tworzenie obiektu komentarza
        Comment comment = new Comment();
        comment.setUsername(username);
        comment.setText(commentText);
        comment.setRating(rating);

        // Zapisywanie komentarza do przedmiotu
        itemService.saveComment(comment, itemId);

        // Przekierowywanie do strony produktu po dodaniu komentarza
        return "redirect:/products/" + itemId;
    }

    // Obsługa żądania GET do wyświetlenia przedmiotów z danej kategorii
    @GetMapping("/category/{category}")
    public String showCategoryItems(@PathVariable String category, Model model) {
        // Pobieranie przedmiotów z określonej kategorii
        List<Item> categoryItems = cartService.getCategoryItems(category);
        model.addAttribute("items", categoryItems);
        return "index";
    }
}