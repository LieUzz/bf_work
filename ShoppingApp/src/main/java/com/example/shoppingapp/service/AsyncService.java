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

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public AsyncService(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    public UsersAndOrders getAllUserForAsync() {
        CompletableFuture<List<UserResult>> usersFuture = userService.getAllUserForAsync();
        CompletableFuture<List<OrderResult>> ordersFuture = orderService.getAllOrderForAsync();
        CompletableFuture<UsersAndOrders> responseFuture = CompletableFuture.allOf(
                usersFuture,
                ordersFuture
        ).thenApply(
                (placeholder) -> {
                    return UsersAndOrders.builder()
                            .users(usersFuture.join())
                            .orders(ordersFuture.join())
                            .build();
                });
        return responseFuture.join();
    }
}
