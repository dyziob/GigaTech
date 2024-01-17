package com.gigatech.shop.service;
// Interfejs ItemService definiuje operacje związane z elementami sklepu.
import com.gigatech.shop.model.Item;
import com.gigatech.shop.model.Comment;


public interface ItemService {
    // Metoda zwracająca element sklepu na podstawie jego identyfikatora.
    Item findItemById(long id);
    // Metoda zapisująca nowy komentarz do elementu sklepu o określonym identyfikatorze.
    void saveComment(Comment comment, long itemId);
}
