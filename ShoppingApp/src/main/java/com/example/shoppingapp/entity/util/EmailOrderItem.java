package com.example.shoppingapp.entity.util;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class EmailOrderItem {
    String name;
    int quantity;
}
