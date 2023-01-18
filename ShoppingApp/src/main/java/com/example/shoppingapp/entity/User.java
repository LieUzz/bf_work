package com.example.shoppingapp.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="permission_id")
    private Permission permission;

    @OneToMany(mappedBy="user")
    private List<Order> orders;
}

