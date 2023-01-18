package com.example.quizweb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "QUIZ")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "cat_id")
    private Integer cat_id;
    @Column(name = "quiz_name")
    private String quiz_name;
    @Column(name = "start_time")
    private Timestamp start_time;
    @Column(name = "end_time")
    private Timestamp end_time;
}