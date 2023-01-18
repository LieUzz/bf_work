package com.example.shoppingapp.domain.response.product;


import com.example.shoppingapp.domain.ServiceStatus;
import com.example.shoppingapp.entity.Product;
import com.example.shoppingapp.entity.util.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AllProductResponse {
    ServiceStatus serviceStatus;
    @JsonView({View.Public.class, View.Internal.class})
    List<Product> products;
}
