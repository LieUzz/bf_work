package com.example.quizweb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "QUIZ_QUESTION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizQuestionHibernate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "quiz_id")
    private int quiz_id;
    @Column(name = "question_id")
    private int question_id;
    @Column(name = "user_choice_id")
    private Integer user_choice_id;
}
