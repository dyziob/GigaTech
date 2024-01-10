package com.gigatech.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    public String firstName;
    public String lastName;
    public String address;
    public String postCode;
    public String city;

}
