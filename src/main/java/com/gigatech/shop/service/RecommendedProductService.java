package com.gigatech.shop.service;

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

    private final ItemRepository itemRepository;
    private final CartService cartService;

    @Autowired
    public RecommendedProductService(ItemRepository itemRepository, CartService cartService) {
        this.itemRepository = itemRepository;
        this.cartService = cartService;
    }

    @Scheduled(fixedRate = 60000)
    public void updateRecommendedProduct() {
        List<Item> allItems = itemRepository.findAll();
        if (!allItems.isEmpty()) {
            Random random = new Random(System.currentTimeMillis());
            int randomIndex = random.nextInt(allItems.size());
            Item recommendedProduct = allItems.get(randomIndex);

            cartService.setProductOfTheDay(recommendedProduct);
        }
    }
}
