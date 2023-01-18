package com.example.quizweb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "cat_name")
    private String category_name;
}
