package com.gigatech.shop.service;

import com.gigatech.shop.model.Item;
import com.gigatech.shop.model.Comment;


public interface ItemService {
    Item findItemById(long id);
    void saveComment(Comment comment, long itemId);
}
