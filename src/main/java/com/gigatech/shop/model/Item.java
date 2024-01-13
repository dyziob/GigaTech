package com.gigatech.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


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
    private String category;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Comment> comments;

    private BigDecimal averageRating;

    public Item(String name, BigDecimal price, String description, String imgUrl, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        ImgUrl = imgUrl;
        this.category = category;
    }
}
