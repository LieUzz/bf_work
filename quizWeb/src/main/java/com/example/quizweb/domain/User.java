package com.example.quizweb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "is_admin")
    private boolean is_admin;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
