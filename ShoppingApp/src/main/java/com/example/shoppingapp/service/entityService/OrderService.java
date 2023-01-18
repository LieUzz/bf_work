package com.example.shoppingapp.service.entityService;

import com.example.shoppingapp.dao.OrderDao;
import com.example.shoppingapp.dao.OrderDetailDao;
import com.example.shoppingapp.entity.Order;
import com.example.shoppingapp.entity.OrderDetail;
import com.example.shoppingapp.entity.Product;
import com.example.shoppingapp.entity.User;
import com.example.shoppingapp.entity.util.OrderResult;
import com.example.shoppingapp.entity.util.ProductResult;
import com.example.shoppingapp.entity.util.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
public class OrderService {
    private final OrderDao orderDao;
    private final OrderDetailDao orderDetailDao;
    private final ProductService productService;
//    private final ProductDao productDao;
    private final UserService userService;

    @Autowired
    public OrderService(OrderDao orderDao,
                        OrderDetailDao orderDetailDao,
                        ProductService productService,
                        UserService userService) {
        this.orderDao = orderDao;
        this.orderDetailDao = orderDetailDao;
        this.productService = productService;
        this.userService = userService;
    }

    public Order getOrderById(Integer id) {
        return orderDao.getById(id);
    }

    @Transactional(rollbackFor = { Exception.class })
    public List<Order> getOrdersByUserId(Integer uid) {
        return orderDao.getOrdersByUserId(uid);
    }

    @Transactional(rollbackFor = { Exception.class })
    public Order getMostRecentOrderByUserId(Integer uid) {
        List<Order> orders = orderDao.getOrdersByUserId(uid);
        if (orders.isEmpty())
            return null;
        return orders.get(orders.size()-1);
    }

    @Transactional(rollbackFor = { Exception.class })
    public List<OrderDetail> getOrderDetailByOrderId(Integer id) {
        return orderDetailDao.getOrderDetailsByOrderId(id);
    }

    public Integer createNewOrder(Order order) {
        orderDao.createNewOrder(order);
        for (OrderDetail o : order.getOrderDetails()) {
            o.setOrder(order);
            orderDetailDao.createNewOrderDetail(o);
            Product p = o.getProduct();
            p.setQuantity(p.getQuantity()-o.getQuantity());
            productService.updateProduct(p);
        }
        return order.getId();
    }

    public List<ProductResult> getPopularItemsByUserId(Integer uid) {
        List<Order> orders = getOrdersByUserId(uid).stream()
                .filter(a->a.getOrder_status().equals("Completed") || a.getOrder_status().equals("Processing"))
                .collect(Collectors.toList());
        return getPopularItemsAmountOrders(orders);
    }

    public List<ProductResult> getPopularItemsTotally() {
        List<Order> orders = getAllOrder().stream()
                .filter(a->a.getOrder_status().equals("Completed"))
                .collect(Collectors.toList());
        return getPopularItemsAmountOrders(orders);
    }

    public List<ProductResult> totalItemsCount() {
        List<Order> orders = orderDao.getAllOrders().stream()
                .filter(a->a.getOrder_status().equals("Completed"))
                .collect(Collectors.toList());
        Map<Integer, Integer> map = new HashMap<>();
        for (Order o:orders) {
            for (OrderDetail od:o.getOrderDetails()) {
                Integer pid = od.getId();
                map.put(pid, map.getOrDefault(pid, 0)+od.getQuantity());
            }
        }

        List<ProductResult> productResults = new ArrayList<>();
        for (Integer i: map.keySet()) {
            productResults.add(ProductResult.builder()
                            .pid(i)
                            .productName(productService.getProductById(i).getName())
                            .quantity(map.get(i))
                            .build());
        }
        return productResults;

    }

    public List<ProductResult> getPopularItemsAmountOrders(List<Order> orders) {
//        for (Order order:orders)
//            System.out.println("&&&+ I am order id " + order.getId());
//        System.out.println("*** I am in");
        Map<Integer, Integer> map = new HashMap<>();
        for (Order o:orders) {
            for (OrderDetail od:o.getOrderDetails()) {
                Integer pid = od.getProduct().getId();
                map.put(pid, map.getOrDefault(pid, 0)+od.getQuantity());
            }
        }

        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));

        for (Integer i : map.keySet()) {
            heap.add(i);
            if (heap.size() > 3) heap.poll();
        }

        List<ProductResult> products = new ArrayList<>();

        while (!heap.isEmpty()) {

            Product p = productService.getProductById(heap.poll());
            products.add(ProductResult.builder()
                    .pid(p.getId())
                    .productName(p.getName())
                    .quantity(map.get(p.getId()))
                    .build());
        }
        return products;
    }

    public boolean cancelOrder(Integer id) {
        Order order = orderDao.getById(id);
        if (order.getOrder_status().equals("Processing")) {
            order.setOrder_status("Canceled");
            orderDao.updateOrder(order);
            for (OrderDetail o : order.getOrderDetails()) {
                Product p = o.getProduct();
                p.setQuantity(p.getQuantity() + o.getQuantity());
                productService.updateProduct(p);
            }
            return true;
        }
        return false;

    }

    public boolean completeOrder(Integer id) {
        Order order = orderDao.getById(id);
        if (order.getOrder_status().equals("Processing")) {
            order.setOrder_status("Completed");
            orderDao.updateOrder(order);
            return true;
        }
        return false;
    }

    public List<Order> getAllOrder() {
        return orderDao.getAllOrders();
    }

    public List<OrderResult> convertOrderToResult(List<Order> orders) {
        List<OrderResult> orderResponseList = new ArrayList<>();
        for (Order o : orders) {
            orderResponseList.add(convertOrderToResult(o));
        }
        return orderResponseList;
    }
    public OrderResult convertOrderToResult(Order o) {
            return OrderResult.builder()
                    .order_time(o.getOrder_time())
                    .user_name(o.getUser().getUsername())
                    .order_status(o.getOrder_status())
                    .user_id(o.getUser().getId())
                    .order_id(o.getId())
                    .build();

    }

    public List<UserResult> getTopBuyer() {

        List<Order> orders = orderDao.getAllOrders().stream()
                .filter(a->a.getOrder_status().equals("Completed"))
                .collect(Collectors.toList());

        Map<Integer, Integer> map = new HashMap<>();
        for (Order o:orders) {
            Integer uid = o.getUser().getId();
            map.put(uid, map.getOrDefault(uid, 0)+1);
        }
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));

        for (Integer i : map.keySet()) {
            heap.add(i);
            if (heap.size() > 3) heap.poll();
        }

        List<UserResult> userResults = new ArrayList<>();

        while (!heap.isEmpty()) {
            User u = userService.getUserById(heap.poll());
            userResults.add(UserResult.builder()
                            .email(u.getEmail())
                            .id(u.getId())
                            .username(u.getUsername())

                        .build());
        }
        return userResults;

    }



    public List<OrderResult> getAllOrderForNonAsync() {
        return orderDao.getAllOrdersForAsync();
    }

    @Async("taskExecutor")
    @Transactional(rollbackFor = { Exception.class })
    public CompletableFuture<List<OrderResult>> getAllOrderForAsync() {
        return CompletableFuture.completedFuture(orderDao.getAllOrdersForAsync());
    }




}
