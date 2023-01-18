package com.example.emailapp.config;

import com.example.emailapp.dao.EmailService;
import com.example.emailapp.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RabbitListener implements MessageListener {

    @Autowired
    private EmailService emailService;
    @Override
    public void onMessage(Message message) {

        System.out.println("New message received from "
                + message.getMessageProperties().getConsumerQueue()
                + ": " + new String(message.getBody()));

        ObjectMapper mapper = new ObjectMapper();
        EmailOrder order = null;
        try {
            order = mapper.readValue(message.getBody(), EmailOrder.class);
            System.out.println("***" + order.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (order!=null) {
                EmailDetails details = EmailDetails.builder()
                        .recipient(order.getEmail())
                        .subject("Your recent Order")
                        .msgBody(new String(message.getBody()))
                        .build();
                System.out.println("***" + details.toString());
                String status = emailService.sendSimpleMail(details);
                System.out.println("status" + status);
            }
        }

        // sending email
        // verify info
        // etc

    }
    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Bean
    public Queue Queue1(){
        Queue queue = new Queue("emailQueue", true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }
}





