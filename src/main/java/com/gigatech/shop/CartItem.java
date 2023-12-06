package com.gigatech.shop;

import com.gigatech.shop.model.Item;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CartItem {
    private Item item;
    private int counter;
    private BigDecimal price;

    public CartItem(Item item){
        this.item = item;
        this.counter = 1;
        this.price = item.getPrice();
    }

    public void increaseCounter(){
        counter++;
        recalculate();
    }

    public void decreaseCounter(){
        if (counter > 0) {
            counter--;
            recalculate();
        }
    }

    private void recalculate() {
        price = item.getPrice().multiply(new BigDecimal(counter));
    }

    public boolean zeroItems() {
        return counter == 0;
    }
}
