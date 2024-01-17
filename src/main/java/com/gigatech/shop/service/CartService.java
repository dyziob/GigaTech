package com.gigatech.shop.service;
// Klasa CartService obsługuje operacje związane z koszykiem zakupowym.
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
    // Repozytorium elementów sklepu.
    private final ItemRepository itemRepository;
    // Koszyk zakupowy.
    private final Cart cart;
    // Produkt dnia.
    @Getter
    @Setter
    private Item productOfTheDay;

    // Wstrzykiwanie zależności.
    @Autowired
    public CartService(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    // Metoda zwracająca listę wszystkich elementów sklepu.
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Metoda wykonująca operację na elemencie koszyka zakupowego (dodawanie, usuwanie, zmniejszanie ilości).
    public void ItemOperation(long itemId, ItemOperation itemOperation) {
        // Pobranie elementu sklepu na podstawie identyfikatora.
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            // Wykonanie odpowiedniej operacji w zależności od przekazanej operacji.
            switch (itemOperation) {
                case INCREASE -> cart.addItem(item);
                case DECREASE -> cart.decreaseItem(item);
                case REMOVE -> cart.removeAllItems(item);
                default -> throw new IllegalArgumentException("Nie można wykonać takiej operacji");
            }
        } else {
            throw new IllegalArgumentException("Nie ma takiego ID");
        }
    }

    // Metoda zwracająca listę elementów sklepu należących do określonej kategorii.
    public List<Item> getCategoryItems(String category) {
        return itemRepository.findByCategory(category);
    }
}
