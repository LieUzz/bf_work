package com.example.shoppingapp.controller;


import com.example.shoppingapp.domain.ServiceStatus;
import com.example.shoppingapp.domain.request.ProductCreationRequest;
import com.example.shoppingapp.domain.request.ProductModifyRequest;
import com.example.shoppingapp.domain.response.MessageResponse;
import com.example.shoppingapp.domain.response.UserResultResponse;
import com.example.shoppingapp.domain.response.order.AllOrderResponse;
import com.example.shoppingapp.domain.response.order.OrderResponse;
import com.example.shoppingapp.domain.response.product.AllProductResponse;
import com.example.shoppingapp.domain.response.product.ProductResultResponse;
import com.example.shoppingapp.domain.response.product.ProductResponse;
import com.example.shoppingapp.entity.Order;
import com.example.shoppingapp.entity.Product;
import com.example.shoppingapp.entity.util.ProductResult;
import com.example.shoppingapp.entity.util.View;
import com.example.shoppingapp.service.entityService.OrderService;
import com.example.shoppingapp.service.entityService.ProductService;
import com.example.shoppingapp.service.entityService.UserService;
import com.fasterxml.jackson.annotation.JsonView;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {

    private ProductService productService;
    private OrderService orderService;
    private UserService userService;

    public AdminController(ProductService productService, OrderService orderService, UserService userService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("product/getAll")
//    @PreAuthorize("hasAuthority('admin')")
    @JsonView(View.Internal.class)
    public AllProductResponse getAllProduct() {
        List<Product> products = productService.getAllProducts();
        return AllProductResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .products(products)
                .build();
    }

    @GetMapping("order/getAll")
//    @PreAuthorize("hasAuthority('admin')")
    public AllOrderResponse getAllOrder() {
        List<Order> orders = orderService.getAllOrder();

        return AllOrderResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .orders(orderService.convertOrderToResult(orders))
                .build();
    }

    @GetMapping("product/get/{id}")
//    @PreAuthorize("hasAuthority('admin')")
    @JsonView(View.Internal.class)
    public ProductResponse getProductById(@PathVariable Integer id) {
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

    @PutMapping("/product/{id}/modify")
//    @PreAuthorize("hasAuthority('admin')")
    public MessageResponse cancelOrder(@PathVariable Integer id,
                                       @RequestBody ProductModifyRequest request) {
        Product product = productService.getProductById(id);
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        productService.updateProduct(product);
        return MessageResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .message("Product " + id + " modify success")
                .build();
    }


    @PostMapping("product/create")
//    @PreAuthorize("hasAuthority('admin')")
    public MessageResponse postCreateOrder(@RequestBody ProductCreationRequest productCreationRequest) {
        Product product = Product.builder()
                .name(productCreationRequest.getName())
                .description(productCreationRequest.getDescription())
                .price(productCreationRequest.getPrice())
                .quantity(productCreationRequest.getQuantity())
                .build();
        productService.createProduct(product);
        return MessageResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .message("Product create success")
                .build();
    }

    @PutMapping("/order/{id}/complete")
//    @PreAuthorize("hasAuthority('admin')")
    public MessageResponse completeOrder(@PathVariable Integer id){
        if (orderService.completeOrder(id)) {

            return MessageResponse.builder()
                    .serviceStatus(
                            ServiceStatus.builder()
                                    .success(true)
                                    .build()
                    )
                    .message("Order " + id + " is completed")
                    .build();
        }
        return MessageResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .message("Order complete failed")
                .build();
    }

    @PutMapping("/order/{id}/cancel")
//    @PreAuthorize("hasAuthority('admin')")
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


    @GetMapping("order/get/{id}")
//    @PreAuthorize("hasAuthority('admin')")
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

    @GetMapping("/get/popular")
//    @PreAuthorize("hasAuthority('admin')")
    public ProductResultResponse getPopularItems(){

        List<ProductResult> products = orderService.getPopularItemsTotally();

        return ProductResultResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .products(products)
                .build();

    }

    @GetMapping("order/product/count")
//    @PreAuthorize("hasAuthority('admin')")
    public ProductResultResponse getAllSoldItemsCount(){

        List<ProductResult> products = orderService.totalItemsCount();

        return ProductResultResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .products(products)
                .build();

    }

    @GetMapping("/get/top/buyer")
//    @PreAuthorize("hasAuthority('admin')")
    public UserResultResponse getTopBuyer(){

//        List<UserResult> userResults = orderService.getTopBuyer();

        return UserResultResponse.builder()
                .serviceStatus(
                        ServiceStatus.builder()
                                .success(true)
                                .build()
                )
                .users(orderService.getTopBuyer())
                .build();

    }
}
