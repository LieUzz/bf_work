package com.example.quizweb.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.quizweb.domain.Feedback;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FeedbackRowMapper implements RowMapper<Feedback> {


    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getInt("id"));
        feedback.setStar_rating(rs.getInt("star_rating"));
        feedback.setFeedback_description(rs.getString("feedback_description"));
        return feedback;
    }

}