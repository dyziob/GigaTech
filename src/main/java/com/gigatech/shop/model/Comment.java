package com.gigatech.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String text;
    private String username;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


}
