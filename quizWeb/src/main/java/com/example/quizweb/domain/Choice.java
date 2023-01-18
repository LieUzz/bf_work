package com.example.quizweb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "CHOICE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Choice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "question_id")
    private int question_id;
    @Column(name = "choice_description")
    private String description;
    @Column(name = "is_correct")
    private boolean is_correct;
}