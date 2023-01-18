package com.example.login.entity;

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
    private int id;

    @Column(name = "permission_description")
    private String permission_description;
}
