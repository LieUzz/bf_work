package com.example.shoppingapp.entity.util;

import com.example.shoppingapp.entity.Order;
import com.example.shoppingapp.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class UsersAndOrders {
    List<UserResult> users;
    List<OrderResult> orders;
}
