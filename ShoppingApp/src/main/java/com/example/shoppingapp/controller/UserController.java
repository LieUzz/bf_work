package com.example.shoppingapp.controller;

import com.example.shoppingapp.domain.request.OrderCreationRequest;
import com.example.shoppingapp.domain.request.OrderDetailCreationRequest;
import com.example.shoppingapp.domain.response.*;
import com.example.shoppingapp.domain.response.order.AllOrderResponse;
import com.example.shoppingapp.domain.response.order.OrderDetailResponse;
import com.example.shoppingapp.domain.response.order.OrderResponse;
import com.example.shoppingapp.domain.response.product.AllProductResponse;
import com.example.shoppingapp.domain.response.product.ProductResultResponse;
import com.example.shoppingapp.domain.response.product.ProductResponse;
import com.example.shoppingapp.entity.*;
import com.example.shoppingapp.entity.util.OrderDetailResult;
import com.example.shoppingapp.entity.util.ProductResult;
import com.example.shoppingapp.exception.NotEnoughInventory;
import com.example.shoppingapp.service.entityService.OrderService;
import com.example.shoppingapp.service.entityService.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import com.example.shoppingapp.entity.util.View;
import com.example.shoppingapp.domain.ServiceStatus;
import com.example.shoppingapp.service.entityService.ProductService;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private ProductService productService;
    private OrderService orderService;
    private UserService userService;

    public UserController(ProductService productService, OrderService orderService, UserService userService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("product/getAll")
//    @PreAuthorize("hasAuthority('user')")
    @JsonView(View.Public.class)
    public AllProductResponse getAllProduct(){
        List<Product> products = productService.getAllProductsWithoutOutOfStock();
//        System.out.println(products.size());
        return AllProductResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .products(products)
                .build();
    }

    @GetMapping("product/get/{id}")
//    @PreAuthorize("hasAuthority('user')")
    @JsonView(View.Public.class)
    public ProductResponse getProductById(@PathVariable Integer id){
        Product product = productService.getProductById(id);
        return ProductResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .product(product)
                .build();
    }


    @PostMapping("order/create")
//    @PreAuthorize("hasAuthority('user')")
    public MessageResponse postCreateOrder(@RequestBody OrderCreationRequest orderCreationRequest) throws NotEnoughInventory {
//        System.out.println("uid: " + orderCreationRequest.getUid());
//        System.out.println("size: " + orderCreationRequest.getOrderDetails().size());

        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetailCreationRequest o : orderCreationRequest.getOrderDetails()) {
            if (o.getQuantity() >  productService.getProductById(o.getPid()).getQuantity()) {
                throw new NotEnoughInventory("Product " + o.getPid() + " out of stock! Create Order Failed");
            }
            orderDetails.add(OrderDetail.builder()
                            .product(productService.getProductById(o.getPid()))
                            .quantity(o.getQuantity())
                            .build());
        }
        long time = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(time);
        Order order = Order.builder()
                .user(userService.getUserById(orderCreationRequest.getUid()))
                .order_time(timestamp)
                .order_status("Processing")
                .orderDetails(orderDetails)
                .build();
        orderService.createNewOrder(order);

        return MessageResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .message("Create Order Success")
                .build();

    }


    @GetMapping("order/get/{id}")
//    @PreAuthorize("hasAuthority('user')")
    public OrderResponse getOrderById(@PathVariable Integer id){
        Order order = orderService.getOrderById(id);
        return OrderResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .order(orderService.convertOrderToResult(order))
                .build();
    }

    @GetMapping("order/get/user/{id}")
//    @PreAuthorize("hasAuthority('user')")
    public AllOrderResponse getOrderByUserId(@PathVariable Integer id){
        List<Order> orders = orderService.getOrdersByUserId(id);
        return AllOrderResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .orders(orderService.convertOrderToResult(orders))
                .build();
    }

    @GetMapping("order/get/{id}/detail")
//    @PreAuthorize("hasAuthority('user')")
    public OrderDetailResponse getOrderDetailsByOrderId(@PathVariable Integer id){
        List<OrderDetail> orderDetails = orderService.getOrderDetailByOrderId(id);
        List<OrderDetailResult> orders = new ArrayList<>();
        for (OrderDetail o:orderDetails) {
            orders.add(OrderDetailResult.builder()
                            .quantity(o.getQuantity())
                            .orderId(o.getId())
                            .productId(o.getProduct().getId())
                            .productName(o.getProduct().getName())
                            .build());
        }
        return OrderDetailResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .orderItems(orders)
                .build();
    }

    @GetMapping("/get/{id}/popular")
//    @PreAuthorize("hasAuthority('user')")
    public ProductResultResponse getPopularItemsByUserId(@PathVariable Integer id){

        List<ProductResult> products = orderService.getPopularItemsByUserId(id);

        return ProductResultResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .products(products)
                .build();

    }


    @PutMapping("/order/{id}/cancel")
//    @PreAuthorize("hasAuthority('user')")
    public MessageResponse cancelOrder(@PathVariable Integer id){
        if (orderService.cancelOrder(id)) {

            return MessageResponse.builder()
                    .serviceStatus(
                            ServiceStatus.builder()
                                    .success(true)
                                    .build()
                    )
                    .message("Order " + id + " is canceled")
                    .build();

        }
        return MessageResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .message("Order cancel failed")
                .build();
    }

}
