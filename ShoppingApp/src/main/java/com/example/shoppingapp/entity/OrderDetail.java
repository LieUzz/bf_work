package com.example.shoppingapp.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_DETAIL")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;
}
