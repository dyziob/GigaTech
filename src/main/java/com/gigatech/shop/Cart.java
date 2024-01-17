package com.gigatech.shop;
// Klasa Cart reprezentuje koszyk zakupowy, przechowujący informacje o elementach, ilości i łącznej cenie.
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
    // Lista elementów w koszyku.
    private List<CartItem> cartItems = new ArrayList<>();

    // Licznik ogólnej ilości produktów w koszyku.
    private int counter = 0;

    // Łączna cena wszystkich produktów w koszyku.
    private BigDecimal sum = BigDecimal.ZERO;

    // Metoda dodająca produkt do koszyka.
    public void addItem(Item item) {
        // Sprawdzenie, czy produkt już istnieje w koszyku.
        getCartItemByItem(item).ifPresentOrElse(
                CartItem::increaseCounter,
                () -> cartItems.add(new CartItem(item))
        );
        // Przeliczenie łącznej ceny i ilości produktów w koszyku.
        recalculatePriceAndCounter();
    }

    // Metoda zmniejszająca ilość danego produktu w koszyku.
    public void decreaseItem(Item item) {
        // Sprawdzenie, czy produkt istnieje w koszyku.
        Optional<CartItem> cartItemOptional = getCartItemByItem(item);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItem.decreaseCounter();
            // Usunięcie produktu, jeśli ilość osiągnęła zero.
            if (cartItem.zeroItems()) {
                removeAllItems(item);
            }
        }
        // Przeliczenie łącznej ceny i ilości produktów w koszyku.
        recalculatePriceAndCounter();
    }

    // Metoda usuwająca wszystkie sztuki danego produktu z koszyka.
    public void removeAllItems(Item item) {
        cartItems.removeIf(i -> i.id_Equal(item));
        // Przeliczenie łącznej ceny i ilości produktów w koszyku.
        recalculatePriceAndCounter();
    }

    // Metoda prywatna, przeliczająca łączną cenę i ilość produktów w koszyku.
    private void recalculatePriceAndCounter() {
        sum = cartItems.stream().map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        counter = cartItems.stream().map(CartItem::getCounter)
                .reduce(0, Integer::sum);
    }

    // Metoda prywatna, zwracająca element koszyka dla danego produktu (jeśli istnieje).
    private Optional<CartItem> getCartItemByItem(Item item) {
        return cartItems.stream()
                .filter(i -> i.id_Equal(item))
                .findFirst();
    }

    // Metoda usuwająca wszystkie elementy z koszyka.
    public void cleanCart() {
        cartItems.clear();
        counter = 0;
        sum = BigDecimal.ZERO;
    }
}
