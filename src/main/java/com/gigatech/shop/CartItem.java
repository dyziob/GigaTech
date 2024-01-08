package com.gigatech.shop;

import com.gigatech.shop.model.Item;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class CartItem {
    private final Item item;
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

    public boolean id_Equal(Item item){
        return Objects.equals(this.item.getId(), item.getId());
        //return this.item.getId().equals(item.getId());
    }
}
