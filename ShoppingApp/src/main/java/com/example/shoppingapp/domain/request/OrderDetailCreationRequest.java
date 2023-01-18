package com.example.shoppingapp.domain.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailCreationRequest {
    Integer pid;
    Integer quantity;
}
