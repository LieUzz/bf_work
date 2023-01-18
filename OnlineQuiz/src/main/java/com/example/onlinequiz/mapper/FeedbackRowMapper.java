package com.example.onlinequiz.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.onlinequiz.domain.Feedback;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackRowMapper implements RowMapper<Feedback> {

    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getInt("id"));
        feedback.setStar(rs.getInt("star"));
        feedback.setText(rs.getString("fbtext"));
        return feedback;
    }
}
