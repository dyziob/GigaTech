package com.gigatech.shop.service;

import com.gigatech.shop.model.Item;
import com.gigatech.shop.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item findItemById(long id) {
        return itemRepository.findById(id).orElse(null);
    }

}
