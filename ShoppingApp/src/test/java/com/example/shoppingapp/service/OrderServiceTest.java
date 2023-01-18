package com.example.shoppingapp.service;

import com.example.shoppingapp.dao.*;
import com.example.shoppingapp.entity.*;
import com.example.shoppingapp.entity.Order;
import com.example.shoppingapp.entity.util.ProductResult;
import com.example.shoppingapp.entity.util.UserResult;
import com.example.shoppingapp.service.entityService.OrderService;
import com.example.shoppingapp.service.entityService.ProductService;
import com.example.shoppingapp.service.entityService.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Disabled
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    ProductService productService;

    @Mock
    UserService userService;

    //    @Spy
    @Mock
    OrderDao orderDao;

    @Mock
    OrderDetailDao orderDetailDao;


    Order mockOrder;
    User mockUser;
    Timestamp mockTimestamp;

    OrderDetail mockOrderDetail;
    OrderDetail mockOrderDetail2;
    OrderDetail mockOrderDetail3;

    Order mockOrder2;

    Product mockProduct;
    Product mockProduct2;
    Product mockProduct3;

    ProductResult mockProductResult;
    ProductResult mockProductResult2;
    ProductResult mockProductResult3;

    List<Order> mockOrders = new ArrayList<>();
    List<OrderDetail> mockOrderDetails = new ArrayList<>();
    List<OrderDetail> mockOrderDetails2 = new ArrayList<>();

    List<ProductResult> mockProductResults = new ArrayList<>();

    List<ProductResult> mockProductResultsForTotal = new ArrayList<>();
    List<UserResult> mockUsers = new ArrayList<>();


    @BeforeEach
    public void setup() {
        long time = System.currentTimeMillis();
        mockTimestamp = new Timestamp(time);
        mockUser = User.builder().id(1).email("userTest@email.com").username("userTest").build();
        mockProduct = Product.builder().id(1).description("testDescription")
                .name("testProduct").price(9.99f).quantity(9).build();
        mockProduct2 = Product.builder().id(2).description("testDescription2")
                .name("testProduct2").price(2.22f).quantity(12).build();
        mockProduct3 = Product.builder().id(3).description("testDescription3")
                .name("testProduct3").price(3.33f).quantity(13).build();
        mockOrder = Order.builder().id(1).user(mockUser).order_status("Processing").order_time(mockTimestamp).build();
        mockOrder2 = Order.builder().id(2).user(mockUser).order_status("Completed").order_time(mockTimestamp).build();
        mockOrders.add(mockOrder);
        mockOrders.add(mockOrder2);

        mockOrderDetail = OrderDetail.builder().id(1).order(mockOrder).product(mockProduct).quantity(1).build();
        mockOrderDetail2 = OrderDetail.builder().id(2).order(mockOrder).product(mockProduct2).quantity(2).build();
        mockOrderDetails.add(mockOrderDetail);
        mockOrderDetails.add(mockOrderDetail2);

        mockOrderDetail3 = OrderDetail.builder().id(3).order(mockOrder2).product(mockProduct3).quantity(3).build();
        mockOrderDetails2.add(mockOrderDetail3);

        mockOrder.setOrderDetails(mockOrderDetails);
        mockOrder2.setOrderDetails(mockOrderDetails2);

        mockProductResult = ProductResult.builder().pid(1).productName("testProduct").quantity(1).build();
        mockProductResult2 = ProductResult.builder().pid(2).productName("testProduct2").quantity(2).build();
        mockProductResult3 = ProductResult.builder().pid(3).productName("testProduct3").quantity(3).build();

        mockProductResults.add(mockProductResult);
        mockProductResults.add(mockProductResult2);
        mockProductResults.add(mockProductResult3);
        mockUsers.add(UserResult.builder().id(mockUser.getId()).email(mockUser.getEmail()).username(mockUser.getUsername()).build());
        mockProductResultsForTotal.add(mockProductResult3);
    }


    @Test
    public void testGetOrderById_success() {
        when(orderDao.getById(1)).thenReturn(mockOrder); //means we do not actual call employeeDAO.getEmployeeById(1)
        Order order = orderService.getOrderById(1);
        assertEquals(order, mockOrder);
    }

    @Test
    public void testGetOrderByUserId_success() {
        when(orderDao.getOrdersByUserId(1)).thenReturn(mockOrders); //means we do not actual call employeeDAO.getEmployeeById(1)
        List<Order> orders = orderService.getOrdersByUserId(1);
        for (int i = 0; i < orders.size(); i++)
            assertEquals(orders.get(i), mockOrders.get(i));
    }

    @Test
    public void testGetOrderDetailById_success() {
        when(orderDetailDao.getOrderDetailsByOrderId(1)).thenReturn(mockOrderDetails); //means we do not actual call employeeDAO.getEmployeeById(1)
        List<OrderDetail> orderDetails = orderService.getOrderDetailByOrderId(1);
        for (int i = 0; i < orderDetails.size(); i++)
            assertEquals(orderDetails.get(i), mockOrderDetails.get(i));
    }

    @Test
    public void testGetPopularItemsAmountOrders_success() {
        when(productService.getProductById(1)).thenReturn(mockProduct);
        when(productService.getProductById(2)).thenReturn(mockProduct2);
        when(productService.getProductById(3)).thenReturn(mockProduct3);
        List<ProductResult> productResults = orderService.getPopularItemsAmountOrders(mockOrders);

        for (int i = 0; i < productResults.size(); i++) {
            assertEquals(productResults.get(i), mockProductResults.get(i));
        }
    }

    @Test
    public void testGetAllOrder_success() {
        when (orderDao.getAllOrders()).thenReturn(mockOrders);
        List<Order> orders = orderService.getAllOrder();
        for (int i = 0; i < orders.size(); i++) {
            assertEquals(orders.get(i), mockOrders.get(i));
        }
    }

    @Test
    public void testGetTopBuyer_success() {
        when(orderDao.getAllOrders()).thenReturn(mockOrders);
        when(userService.getUserById(1)).thenReturn(mockUser);

        List<UserResult> userResults = orderService.getTopBuyer();
        for (int i = 0; i < userResults.size(); i++) {
            assertEquals(userResults.get(i), mockUsers.get(i));
        }
    }

    @Test
    public void testTotalItemsCount_success() {
        when(orderDao.getAllOrders()).thenReturn(mockOrders);
        when(productService.getProductById(3)).thenReturn(mockProduct3);
        List<ProductResult> productResults = orderService.totalItemsCount();
        for (int i = 0; i < productResults.size(); i++) {
            assertEquals(productResults.get(i), mockProductResultsForTotal.get(i));
        }
    }




}
