package com.example.shoppingapp.entity;


import com.fasterxml.jackson.annotation.JsonView;
import com.example.shoppingapp.entity.util.View;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    @Column(name = "id")
    private Integer id;

    @JsonView(View.Public.class)
    @Column(name = "name")
    private String name;

    @JsonView(View.Public.class)
    @Column(name = "description")
    private String description;

    @JsonView(View.Public.class)
    @Column(name = "price")
    private float price;

    @JsonView(View.Internal.class)
    @Column(name = "quantity")
    private int quantity;
}
