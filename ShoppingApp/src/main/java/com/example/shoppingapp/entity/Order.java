package com.example.shoppingapp.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_time")
    private Timestamp order_time;

    @Column(name = "order_status")
    private String order_status;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToMany(mappedBy="order")
    private List<OrderDetail> orderDetails;

}
