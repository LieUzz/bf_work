package com.example.emailapp.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmailOrder {
    String email;
    Float total_price;
    private Timestamp order_time;
    List<EmailOrderItem> items;
}