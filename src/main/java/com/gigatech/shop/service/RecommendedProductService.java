package com.gigatech.shop.service;
// Klasa RecommendedProductService obsługuje aktualizację rekomendowanego produktu.
import com.gigatech.shop.Repository.ItemRepository;
import com.gigatech.shop.model.Item;
import com.gigatech.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RecommendedProductService {
    // Repozytorium elementów sklepu.
    private final ItemRepository itemRepository;
    // Serwis obsługujący koszyk zakupowy.
    private final CartService cartService;
    // Wstrzykiwanie zależności.
    @Autowired
    public RecommendedProductService(ItemRepository itemRepository, CartService cartService) {
        this.itemRepository = itemRepository;
        this.cartService = cartService;
    }
    // Metoda zaplanowana na regularne wykonywanie aktualizacji rekomendowanego produktu.
    @Scheduled(fixedRate = 43200000) // Wykonywane co 12 godzin (12h * 60min * 60s * 1000ms).
    public void updateRecommendedProduct() {
        // Pobranie wszystkich elementów sklepu.
        List<Item> allItems = itemRepository.findAll();
        if (!allItems.isEmpty()) {
            // Losowy wybór indeksu z listy elementów.
            Random random = new Random(System.currentTimeMillis());
            int randomIndex = random.nextInt(allItems.size());
            // Pobranie rekomendowanego produktu na podstawie losowego indeksu.
            Item recommendedProduct = allItems.get(randomIndex);
            // Ustawienie rekomendowanego produktu jako "produktu dnia" w koszyku.
            cartService.setProductOfTheDay(recommendedProduct);
        }
    }
}
