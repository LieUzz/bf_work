package com.example.w4d5.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AllQuizResponse {
    private ResponseStatus status;
    private List<Quiz> quizList;
}
