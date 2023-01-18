package com.example.shoppingapp.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PERMISSION")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "permission_description")
    private String permission_description;
}
