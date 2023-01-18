package com.example.quizweb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "FEEDBACK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "star_rating")
    private Integer star_rating;
    @Column(name = "feedback_description")
    private String feedback_description;

    public Feedback(Integer star_rating, String feedback_description) {
        this.star_rating = star_rating;
        this.feedback_description = feedback_description;
    }
}
