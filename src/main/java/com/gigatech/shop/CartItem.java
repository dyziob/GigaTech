package com.gigatech.shop;
// Klasa CartItem reprezentuje element koszyka, zawierający informacje o produkcie, ilości oraz cenie.
import com.gigatech.shop.model.Item;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class CartItem {
    // Pole przechowujące informacje o produkcie.
    private final Item item;
    // Licznik ilości produktu w koszyku.
    private int counter;
    // Cena całkowita za wszystkie sztuki danego produktu.
    private BigDecimal price;
    // Konstruktor klasy, inicjalizujący element koszyka z jedną sztuką produktu.


    public CartItem(Item item){
        this.item = item;
        this.counter = 1;
        this.price = item.getPrice();
    }
    // Metoda zwiększająca licznik produktu w koszyku o 1.
    public void increaseCounter(){
        counter++;
        recalculate();
    }
    // Metoda zmniejszająca licznik produktu w koszyku o 1, jeśli ilość jest większa niż 0.
    public void decreaseCounter(){
        if (counter > 0) {
            counter--;
            recalculate();
        }
    }
    // Metoda prywatna, recalculująca całkowitą cenę produktu na podstawie ilości.
    private void recalculate() {
        price = item.getPrice().multiply(new BigDecimal(counter));
    }
    // Metoda sprawdzająca, czy liczba produktów w koszyku wynosi 0.
    public boolean zeroItems() {
        return counter == 0;
    }
    // Metoda porównująca produkty na podstawie identyfikatorów.
    public boolean id_Equal(Item item){
        return Objects.equals(this.item.getId(), item.getId());
        //return this.item.getId().equals(item.getId());
    }
}
