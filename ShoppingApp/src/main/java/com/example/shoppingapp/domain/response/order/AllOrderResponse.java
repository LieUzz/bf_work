package com.example.shoppingapp.domain.response.order;

import com.example.shoppingapp.domain.ServiceStatus;
import com.example.shoppingapp.entity.util.OrderResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AllOrderResponse {
    ServiceStatus serviceStatus;
    List<OrderResult> orders;
}
