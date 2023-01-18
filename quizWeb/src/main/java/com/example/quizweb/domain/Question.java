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
public class Question {
    private int id;
    private int cat_id;
    private String description;
    private List<Choice> choices;
    private int correctChoiceId;

    private String userChoice;
    private String correctChoice;

}

