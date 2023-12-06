package com.gigatech.shop;

import com.gigatech.shop.model.Item;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Item item) {
        boolean notfound = true;

        for(CartItem cI: cartItems){
            if (Objects.equals(cI.getItem().getId(), item.getId())){
                notfound = false;
                cI.increaseCounter();
                recalculatePriceAndCounter();
                break;
            }
        }

        if(notfound) {
            cartItems.add(new CartItem(item));
            recalculatePriceAndCounter();
        }
    }
    public void removeItem(Item item){
        for (CartItem cI: cartItems){
            if (Objects.equals(cI.getItem().getId(), item.getId())){
                cI.decreaseCounter();
                if(cI.zeroItems()) {
                    cartItems.remove(cI);
                }
                recalculatePriceAndCounter();
                break;
            }
        }
    }

    private void recalculatePriceAndCounter(){
        BigDecimal tempPrice = BigDecimal.ZERO;
        int tempCounter = 0;

        for(CartItem cI: cartItems){
            tempCounter += cI.getCounter();
            tempPrice = tempPrice.add(cI.getPrice());
        }
        this.counter = tempCounter;
        this.sum = tempPrice;
    }
}
