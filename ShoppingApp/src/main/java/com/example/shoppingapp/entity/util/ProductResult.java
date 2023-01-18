package com.example.shoppingapp.entity.util;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@EqualsAndHashCode
public class ProductResult {
    Integer pid;
    String productName;
    Integer quantity;
}
