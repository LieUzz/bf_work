package com.example.shoppingapp.domain;


import com.example.shoppingapp.entity.util.EmailOrderItem;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmailOrderMessage {
    String email;
    Float total_price;
    private Timestamp order_time;
    List<EmailOrderItem> items;
}
