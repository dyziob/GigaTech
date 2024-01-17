package com.gigatech.shop.service;

import com.gigatech.shop.Cart;
import com.gigatech.shop.ItemOperation;
import com.gigatech.shop.Repository.ItemRepository;
import com.gigatech.shop.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private Cart cart;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllItems_shouldReturnListOfItems() {
        // Arrange
        List<Item> items = Arrays.asList(new Item(), new Item());
        when(itemRepository.findAll()).thenReturn(items);

        // Act
        List<Item> result = cartService.getAllItems();

        // Assert
        assertEquals(items, result);
    }

    @Test
    void itemOperation_withValidItemIdAndIncreaseOperation_shouldAddItemToCart() {
        // Arrange
        long itemId = 1L;
        Item item = new Item();
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

        // Act
        cartService.ItemOperation(itemId, ItemOperation.INCREASE);

        // Assert
        verify(cart, times(1)).addItem(item);
    }

    @Test
    void itemOperation_withValidItemIdAndDecreaseOperation_shouldDecreaseItemInCart() {
        // Arrange
        long itemId = 1L;
        Item item = new Item();
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

        // Act
        cartService.ItemOperation(itemId, ItemOperation.DECREASE);

        // Assert
        verify(cart, times(1)).decreaseItem(item);
    }

    @Test
    void itemOperation_withValidItemIdAndRemoveOperation_shouldRemoveItemFromCart() {
        // Arrange
        long itemId = 1L;
        Item item = new Item();
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

        // Act
        cartService.ItemOperation(itemId, ItemOperation.REMOVE);

        // Assert
        verify(cart, times(1)).removeAllItems(item);
    }

    public void ItemOperation(long itemId, ItemOperation itemOperation) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            switch (itemOperation) {
                case INCREASE -> cart.addItem(item);
                case DECREASE -> cart.decreaseItem(item);
                case REMOVE -> cart.removeAllItems(item);
                default -> throw new IllegalArgumentException("Invalid item operation");
            }
        } else {
            throw new IllegalArgumentException("Invalid Item ID");
        }
    }


    // Add more tests for other methods as needed
}
