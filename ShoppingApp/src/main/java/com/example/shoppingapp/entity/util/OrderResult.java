package com.example.shoppingapp.entity.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
public class OrderResult {
    Integer order_id;
    Integer user_id;
    String user_name;
    Timestamp order_time;
    String order_status;
}
