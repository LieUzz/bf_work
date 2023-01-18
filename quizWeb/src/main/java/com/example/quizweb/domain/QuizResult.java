package com.example.quizweb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizResult {
    private Integer id;
    private User user;
    private Category category;
    private String quiz_name;
    private Timestamp start_time;
    private Timestamp end_time;
    private List<Question> questions;
//    private List<String> user_choice_id;
//    private List<String> correct_choice_id;
    private Integer score;
}