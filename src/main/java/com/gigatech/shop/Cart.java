package com.gigatech.shop;

import com.gigatech.shop.model.Item;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Item item) {
        boolean notfound = true;
        getCartItemByItem(item).ifPresentOrElse(
                CartItem::increaseCounter,
                () -> cartItems.add(new CartItem(item))
        );
        recalculatePriceAndCounter();
    }
    public void decreaseItem(Item item){
        Optional<CartItem> Cart_Item = getCartItemByItem(item);
        if (Cart_Item.isPresent()){
            CartItem cartitem = Cart_Item.get();
            cartitem.decreaseCounter();
            if (cartitem.zeroItems()) {
                removeAllItems(item);
            }
        }
                recalculatePriceAndCounter();
    }

    public void removeAllItems(Item item) {
        cartItems.removeIf(i -> i.id_Equal(item));
        recalculatePriceAndCounter();
    }


    private void recalculatePriceAndCounter(){
        sum = cartItems.stream().map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        counter = cartItems.stream().map(CartItem::getCounter)
                .reduce(0, Integer::sum);
    }

    private Optional<CartItem>getCartItemByItem(Item item){
        return cartItems.stream()
                .filter(i -> i.id_Equal(item))
                .findFirst();
    }
}
