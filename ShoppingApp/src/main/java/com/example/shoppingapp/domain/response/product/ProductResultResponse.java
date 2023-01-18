package com.example.shoppingapp.domain.response.product;

import com.example.shoppingapp.domain.ServiceStatus;
import com.example.shoppingapp.entity.util.ProductResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductResultResponse {
    ServiceStatus serviceStatus;
    List<ProductResult> products;
}
