package com.example.shoppingapp.controller;

import com.example.shoppingapp.service.entityService.OrderService;
import com.example.shoppingapp.service.entityService.ProductService;
import com.example.shoppingapp.service.entityService.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@Disabled
@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    ProductService productService;

    @MockBean
    OrderService orderService;

    @BeforeAll
    public static void prepare() {
//        System.out.println("preparing resource");
    }

    @BeforeEach
    public void init() {
//        System.out.println("before each");
    }

    @Test
    public void groupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        assertAll("numbers",
                () -> assertEquals(numbers[0], 0),
                () -> assertEquals(numbers[3], 3),
                () -> assertEquals(numbers[4], 4)
        );
    }

//    @Test
//    public void testAddOrder_failedWhenNoRequestBody() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/order/create")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(403))
//                .andReturn();
//        ServiceStatus serviceStatus = new Gson().fromJson(result.getResponse().getContentAsString(), ServiceStatus.class);
//        assertFalse(serviceStatus.isSuccess());
//        assertNotNull(serviceStatus.getMessage());
//    }

//    @Test
//    public void testGetAllProduct_success() throws Exception {
//        Product mockProduct = Product.builder().id(1).name("testProduct").description("testDescription")
//                .price(9.99f).quantity(9).build();
//        List<Product> mockProducts = new ArrayList<>();
//        mockProducts.add(mockProduct);
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/product/getAll")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//        Type listType = new TypeToken<ArrayList<Product>>(){}.getType();
//        List<Product> products = new Gson().fromJson(result.getResponse().getContentAsString(), listType);
//        for (int i = 0; i < products.size(); i++)
//            assertEquals(products.toString(), mockProducts.toString());
//    }

//    @Test
//    public void testAddOrder_success() throws Exception {
//        System.out.println("*** I am here. 1");
//        long time = System.currentTimeMillis();
//        User mockUser = User.builder().id(1).email("userTest@email.com").username("userTest").build();
//        System.out.println("*** I am here. 2");
//        Product mockProduct = Product.builder().id(1).description("testDescription")
//                .name("testProduct").price(9.99f).quantity(9).build();
//        System.out.println("*** I am here. 3");
//        Order mockOrder = Order.builder().id(1).user(mockUser).order_status("Processing").order_time(new Timestamp(time)).build();
//        System.out.println("*** I am here. 4");
//        List<OrderDetail> orderDetails = new ArrayList<>();
//        OrderDetail orderDetail = OrderDetail.builder().id(1).order(mockOrder).product(mockProduct).quantity(1).build();
//        System.out.println("*** I am here. 5");
//        orderDetails.add(orderDetail);
//        System.out.println("*** I am here. 6");
//        mockOrder.setOrderDetails(orderDetails);
//        System.out.println("*** I am here. 7");
//
//        when(orderService.createNewOrder(any())).thenReturn(1);
//        System.out.println("*** I am here. 8");
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/order/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new Gson().toJson(mockOrder))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn();
//        System.out.println("*** I am here. 9");
//        MessageResponse messageResponse = new Gson().fromJson(result.getResponse().getContentAsString(), MessageResponse.class);
//        System.out.println("*** I am here. 10");
////        assertEquals("Create Order Success", messageResponse.getMessage());
////        assertTrue(messageResponse.getServiceStatus().isSuccess());
//    }

//    @Test
//    public void testCancelOrder() {
//
//    }

//    @Test
//    public void testCancelOrder_failedWhenNoRequestBody() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/employee")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andReturn();
//        ServiceStatus serviceStatus = new Gson().fromJson(result.getResponse().getContentAsString(), ServiceStatus.class);
//        assertFalse(serviceStatus.getSuccess());
//        assertNotNull(serviceStatus.getErrorMessage());
//    }

//    @Test
//    public void testCancelOrder_invalidUserInputRequest() throws Exception {
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employee/{id}", "a")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andReturn();
//        ServiceStatus serviceStatus = new Gson().fromJson(result.getResponse().getContentAsString(), ServiceStatus.class);
//        assertFalse(serviceStatus.getSuccess());
//        assertNotNull(serviceStatus.getErrorMessage());
//    }


}