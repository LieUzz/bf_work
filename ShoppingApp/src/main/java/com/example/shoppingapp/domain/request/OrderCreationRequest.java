package com.example.shoppingapp.domain.request;

import com.example.shoppingapp.entity.OrderDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderCreationRequest {
    Integer uid;
    List<OrderDetailCreationRequest> orderDetails;
}
