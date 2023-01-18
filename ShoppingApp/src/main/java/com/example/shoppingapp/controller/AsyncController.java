package com.example.shoppingapp.controller;

import com.example.shoppingapp.domain.ServiceStatus;
import com.example.shoppingapp.domain.response.MessageResponse;
import com.example.shoppingapp.domain.response.order.AllOrderResponse;
import com.example.shoppingapp.entity.Order;
import com.example.shoppingapp.entity.User;
import com.example.shoppingapp.entity.util.UsersAndOrders;
import com.example.shoppingapp.service.AdminService;
import com.example.shoppingapp.service.AsyncService;
import com.example.shoppingapp.service.entityService.OrderService;
import com.example.shoppingapp.service.entityService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AsyncController {
    private AdminService adminService;
    private AsyncService asyncService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setAsyncService(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("async/getAll")
    public ResponseEntity<UsersAndOrders> getAllAsync() {

        return ResponseEntity.ok(asyncService.getAllUserForAsync());
    }

    @GetMapping("nonAsync/getAll")
    public UsersAndOrders getAllNonAsync() {
        UsersAndOrders usersAndOrders = adminService.getAllUserForNonAsync();
        System.out.println("In Controller getAllUserForNonAsync");
        return usersAndOrders;
    }
}
