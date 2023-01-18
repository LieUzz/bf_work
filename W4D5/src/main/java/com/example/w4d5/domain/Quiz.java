package com.example.w4d5.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Quiz {
    private Integer userId;
    private String quizName;
}
