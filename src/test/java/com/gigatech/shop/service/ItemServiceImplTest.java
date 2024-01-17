package com.gigatech.shop.service;

import com.gigatech.shop.Repository.ItemRepository;
import com.gigatech.shop.model.Comment;
import com.gigatech.shop.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findItemById_withValidItemId_shouldReturnItem() {
        // Arrange
        long itemId = 1L;
        Item expectedItem = new Item();
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(expectedItem));

        // Act
        Item result = itemService.findItemById(itemId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedItem, result);
    }

    @Test
    void findItemById_withInvalidItemId_shouldReturnNull() {
        // Arrange
        long invalidItemId = 999L;
        when(itemRepository.findById(invalidItemId)).thenReturn(Optional.empty());

        // Act
        Item result = itemService.findItemById(invalidItemId);

        // Assert
        assertNull(result);
    }

    @Test
    void saveComment_withValidItemId_shouldSaveComment() {
        // Arrange
        long itemId = 1L;
        Item item = new Item();
        Comment comment = new Comment();

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));
        when(itemRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        itemService.saveComment(comment, itemId);

        // Assert
        verify(itemRepository, times(1)).save(any());
        assertEquals(1, item.getComments().size());
        assertEquals(comment, item.getComments().get(0));
        assertEquals(item, comment.getItem());
    }


    @Test
    void saveComment_withInvalidItemId_shouldNotSaveComment() {
        // Arrange
        long invalidItemId = 999L;
        Comment comment = new Comment();

        when(itemRepository.findById(invalidItemId)).thenReturn(Optional.empty());

        // Act
        itemService.saveComment(comment, invalidItemId);

        // Assert
        verify(itemRepository, never()).save(any());
    }
}

