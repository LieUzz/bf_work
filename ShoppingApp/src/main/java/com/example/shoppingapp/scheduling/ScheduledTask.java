package com.example.shoppingapp.scheduling;

import com.example.shoppingapp.domain.EmailOrderMessage;
import com.example.shoppingapp.entity.Order;
import com.example.shoppingapp.entity.OrderDetail;
import com.example.shoppingapp.entity.util.EmailOrderItem;
import com.example.shoppingapp.entity.util.UserResult;
import com.example.shoppingapp.service.entityService.OrderService;
import com.example.shoppingapp.service.entityService.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTask {

    private RabbitTemplate rabbitTemplate;

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public ScheduledTask(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(cron = "0 0 18 * * *")
//    @Scheduled(fixedRate = 5000)
    public void sendMessageEverySecond() throws JsonProcessingException {
        List<UserResult> users = userService.getAllUsers();
        for (UserResult u:users) {
            Order order = orderService.getMostRecentOrderByUserId(u.getId());
            if (order == null)
                continue;
            List<OrderDetail> orderDetails = orderService.getOrderDetailByOrderId(order.getId());
            EmailOrderMessage emailOrderMessage = EmailOrderMessage.builder()
                    .email(u.getEmail())
                    .order_time(order.getOrder_time())
                    .items(new ArrayList<>())
                    .build();
            float price = 0f;
            for (OrderDetail o : orderDetails) {
                emailOrderMessage.getItems().add(EmailOrderItem.builder()
                        .name(o.getProduct().getName())
                        .quantity(o.getQuantity())
                        .build());
                price += o.getProduct().getPrice() * o.getQuantity();
            }
            emailOrderMessage.setTotal_price(price);
            ObjectMapper mapper = new ObjectMapper();
            String message = mapper.writeValueAsString(emailOrderMessage);
            rabbitTemplate.convertAndSend("emailExchange", "email", message);
        }
    }
}
