package com.example.shoppingapp.domain.response.order;

import com.example.shoppingapp.domain.ServiceStatus;
import com.example.shoppingapp.entity.util.OrderResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderResponse {
    ServiceStatus serviceStatus;
    OrderResult order;
}
