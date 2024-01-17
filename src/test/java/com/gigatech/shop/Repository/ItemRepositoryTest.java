package com.gigatech.shop.Repository;

import com.gigatech.shop.Repository.ItemRepository;
import com.gigatech.shop.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testFindByCategory() {
        // Arrange
        Item item1 = new Item("Test Item 1", new BigDecimal("20.99"), "Description 1", "image1.jpg", "Category A");
        Item item2 = new Item("Test Item 2", new BigDecimal("30.99"), "Description 2", "image2.jpg", "Category A");
        Item item3 = new Item("Test Item 3", new BigDecimal("40.99"), "Description 3", "image3.jpg", "Category B");

        itemRepository.saveAll(List.of(item1, item2, item3));

        // Act
        List<Item> itemsInCategoryA = itemRepository.findByCategory("Category A");
        List<Item> itemsInCategoryB = itemRepository.findByCategory("Category B");

        // Assert
        assertEquals(2, itemsInCategoryA.size());
        assertTrue(itemsInCategoryA.contains(item1));
        assertTrue(itemsInCategoryA.contains(item2));

        assertEquals(1, itemsInCategoryB.size());
        assertTrue(itemsInCategoryB.contains(item3));
    }

    @Test
    public void testFindById() {
        // Arrange
        Item item = new Item("Test Item", new BigDecimal("20.99"), "Test Description", "test-image.jpg", "Test Category");
        itemRepository.save(item);

        // Act
        Optional<Item> foundItem = itemRepository.findById(item.getId());

        // Assert
        assertTrue(foundItem.isPresent());
        assertEquals(item, foundItem.get());
    }

    @Test
    public void testFindById_NotFound() {
        // Act
        Optional<Item> foundItem = itemRepository.findById(-1L);

        // Assert
        assertTrue(foundItem.isEmpty());
    }
}
