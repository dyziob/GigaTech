package com.gigatech.shop.service;

import com.gigatech.shop.Cart;
import com.gigatech.shop.ItemOperation;
import com.gigatech.shop.Repository.ItemRepository;
import com.gigatech.shop.model.Item;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final Cart cart;
    @Getter
    @Setter
    private Item productOfTheDay;

    @Autowired
    public CartService(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void ItemOperation(long itemId, ItemOperation itemOperation) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            switch (itemOperation) {
                case INCREASE -> cart.addItem(item);
                case DECREASE -> cart.decreaseItem(item);
                case REMOVE -> cart.removeAllItems(item);
                default -> throw new IllegalArgumentException();
            }
        }
    }

    public List<Item> getCategoryItems(String category) {
        return itemRepository.findByCategory(category);
    }
}
