package com.example.onlinequiz.domain;

import java.util.List;

public class Quiz {
    private Integer id;

    private String quizType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", quizType='" + quizType + '\'' +
                '}';
    }
}
