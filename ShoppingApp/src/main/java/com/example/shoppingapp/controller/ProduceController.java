package com.example.shoppingapp.controller;

import com.example.shoppingapp.domain.EmailOrderMessage;
import com.example.shoppingapp.entity.Order;
import com.example.shoppingapp.entity.OrderDetail;
import com.example.shoppingapp.entity.util.EmailOrderItem;
import com.example.shoppingapp.service.entityService.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("message")
public class ProduceController {

    private RabbitTemplate rabbitTemplate;

    private OrderService orderService;

    @Autowired
    public ProduceController(OrderService orderService) {
        this.orderService = orderService;
    }
    //    // topic exchange
//    private static String exchangeName = "amq.topic";
//    // exchange type
//    private static String exchangeType= "topic";

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("produce/direct")
    public ResponseEntity<String> produceDirect(@RequestParam Integer uid,
                                                @RequestParam String routingKey){

        Order order = orderService.getMostRecentOrderByUserId(uid);
        List<OrderDetail> orderDetails = orderService.getOrderDetailByOrderId(order.getId());
        EmailOrderMessage emailOrderMessage = EmailOrderMessage.builder()
                .order_time(order.getOrder_time())
                .items(new ArrayList<>())
                .build();
        float price = 0f;
        for (OrderDetail o:orderDetails) {
            emailOrderMessage.getItems().add(EmailOrderItem.builder()
                    .name(o.getProduct().getName())
                    .quantity(o.getQuantity())
                    .build());
            price += o.getProduct().getPrice() * o.getQuantity();
        }
        emailOrderMessage.setTotal_price(price);
        rabbitTemplate.convertAndSend("emailExchange", routingKey, emailOrderMessage.toString());

        return ResponseEntity.ok("Message Sent");
    }

//    @PostMapping("produce/fanout")
//    public ResponseEntity<String> produceFanout(@RequestBody NewMessageRequest request,
//                                                @RequestParam String routingKey) {
//        for(int i = 0; i < 100; i++) {
//            SimpleMessage newMessage = SimpleMessage.builder()
//                    .title(request.getTitle())
//                    .description(request.getDescription())
//                    .build();
//
//            rabbitTemplate.convertAndSend("demo.fanout", routingKey, newMessage.toString()+" "+i);
//        }
//
//        return ResponseEntity.ok("Message Sent");
//    }
//
//    @PostMapping("produce/topic")
//    public ResponseEntity<String> produceTopic(@RequestBody NewMessageRequest request,
//                                               @RequestParam String routingKey) {
//        SimpleMessage newMessage = SimpleMessage.builder()
//                .title(request.getTitle())
//                .description(request.getDescription())
//                .build();
//
//        rabbitTemplate.convertAndSend("demo.topic", routingKey, newMessage.toString());
//
//        return ResponseEntity.ok("Message Sent");
//    }
//
//    @PostMapping("produce/topics")
//    public ResponseEntity<String> produceTopicMoreExamples(@RequestBody NewMessageRequest request,
//                                                           @RequestParam String routingKey) {
//
//
//        for(int i = 0; i < 10; i++) {
//            SimpleMessage newMessage = SimpleMessage.builder()
//                    .title(request.getTitle())
//                    .description(request.getDescription())
//                    .build();
//
//            rabbitTemplate.convertAndSend("amq.topic", routingKey, newMessage.toString()+" "+i);
//        }
//
//        return ResponseEntity.ok("Message Sent");
//    }

}

