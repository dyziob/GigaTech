package com.gigatech.shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Item {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String ImgUrl;

    public Item(String name, BigDecimal price, String description, String imgUrl) {
        this.name = name;
        this.price = price;
        this.description = description;
        ImgUrl = imgUrl;
    }
}
