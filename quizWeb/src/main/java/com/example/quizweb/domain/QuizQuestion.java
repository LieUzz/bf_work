package com.example.quizweb.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizQuestion {
    private int id;
    private int quiz_id;

    private int question_id;
    private Integer user_choice_id;

    private Question question;
    private Choice choice;
    private String is_correct;
}
