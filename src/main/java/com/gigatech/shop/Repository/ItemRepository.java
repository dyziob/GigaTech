package com.gigatech.shop.Repository;

import com.gigatech.shop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Metoda do pobierania listy przedmiotów po kategorii.
    List<Item> findByCategory(String category);
    // Metoda do pobierania przedmiotu po jego identyfikatorze (id).
    Optional<Item> findById(Long id);
}
