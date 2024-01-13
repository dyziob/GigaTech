package com.gigatech.shop.service;

import com.gigatech.shop.model.Item;
import com.gigatech.shop.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gigatech.shop.model.Comment;


import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item findItemById(long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public void saveComment(Comment comment, long itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        itemOptional.ifPresent(item -> {
            comment.setItem(item);
            item.getComments().add(comment);
            itemRepository.save(item);
        });
    }

}
