package com.gigatech.shop.service;

import com.gigatech.shop.Cart;
import com.gigatech.shop.Repository.ItemRepository;
import com.gigatech.shop.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final Cart cart;

    @Autowired
    public CartService(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void addItemToCart(Long itemId){
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            cart.addItem(item);
        }
    }
    public void decreaseItemToCart(Long itemId){
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            cart.decreaseItem(item);
        }
    }

    public void removeItem(Long itemid){
        Optional<Item> oItem = itemRepository.findById(itemid);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            cart.removeAllItems(item);
        }
    }
}
