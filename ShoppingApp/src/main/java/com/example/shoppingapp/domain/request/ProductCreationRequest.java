package com.example.shoppingapp.domain.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCreationRequest {
    String name;
    String description;
    Float price;
    Integer quantity;
}
