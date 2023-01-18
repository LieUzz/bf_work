package com.example.quizweb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "QUESTION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionHibernate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "cat_id")
    private int cat_id;
    @Column(name = "question_description")
    private String description;
}
