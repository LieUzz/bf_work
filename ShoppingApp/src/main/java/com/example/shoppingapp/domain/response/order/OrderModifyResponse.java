package com.example.shoppingapp.domain.response.order;

import com.example.shoppingapp.domain.ServiceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderModifyResponse {
    ServiceStatus serviceStatus;
    String message;
}
