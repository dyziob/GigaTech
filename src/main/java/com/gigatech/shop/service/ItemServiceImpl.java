package com.gigatech.shop.service;
// Klasa ItemServiceImpl implementuje interfejs ItemService i obsługuje operacje związane z elementami sklepu.
import com.gigatech.shop.model.Item;
import com.gigatech.shop.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gigatech.shop.model.Comment;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    // Repozytorium elementów sklepu.
    @Autowired
    private ItemRepository itemRepository;

    // Metoda zwracająca element sklepu na podstawie jego identyfikatora.
    @Override
    public Item findItemById(long id) {
        return itemRepository.findById(id).orElse(null);
    }

    // Metoda zapisująca nowy komentarz do elementu sklepu o określonym identyfikatorze.
    @Override
    public void saveComment(Comment comment, long itemId) {
        // Pobranie elementu sklepu na podstawie identyfikatora.
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        itemOptional.ifPresent(item -> {
            // Przypisanie elementu sklepu do komentarza.
            comment.setItem(item);

            // Inicjalizacja listy komentarzy, jeśli jeszcze nie istnieje.
            if (item.getComments() == null) {
                item.setComments(new ArrayList<>());
            }

            // Dodanie komentarza do listy komentarzy elementu sklepu.
            item.getComments().add(comment);

            // Zapisanie zaktualizowanego elementu sklepu do repozytorium.
            itemRepository.save(item);
        });
    }


}
