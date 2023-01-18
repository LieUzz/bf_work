package com.example.shoppingapp.entity.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDetailResult {
    Integer orderId;
    Integer productId;
    String productName;
    Integer quantity;
}
