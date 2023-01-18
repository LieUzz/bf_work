package com.example.emailapp.entity;

import lombok.*;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmailOrderItem {
    String name;
    int quantity;
}
