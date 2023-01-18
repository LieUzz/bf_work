package com.example.shoppingapp.service;

import com.example.shoppingapp.entity.Order;
import com.example.shoppingapp.entity.User;
import com.example.shoppingapp.entity.util.OrderResult;
import com.example.shoppingapp.entity.util.UserResult;
import com.example.shoppingapp.entity.util.UsersAndOrders;
import com.example.shoppingapp.service.entityService.OrderService;
import com.example.shoppingapp.service.entityService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public AdminService(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    public UsersAndOrders getAllUserForNonAsync() {
        List<UserResult> users = userService.getAllUserForNonAsync();
        List<OrderResult> orders = orderService.getAllOrderForNonAsync();
        return UsersAndOrders.builder()
                .users(users)
                .orders(orders)
                .build();
    }
}
