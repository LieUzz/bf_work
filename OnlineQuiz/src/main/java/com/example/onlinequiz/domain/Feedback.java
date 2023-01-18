package com.example.onlinequiz.domain;

public class Feedback {

    private Integer id;

    private Integer star;

    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
