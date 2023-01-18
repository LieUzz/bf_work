package com.example.shoppingapp.domain.response.order;

import com.example.shoppingapp.domain.ServiceStatus;
import com.example.shoppingapp.entity.util.OrderDetailResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderDetailResponse {
    ServiceStatus serviceStatus;
    List<OrderDetailResult> orderItems;
}
