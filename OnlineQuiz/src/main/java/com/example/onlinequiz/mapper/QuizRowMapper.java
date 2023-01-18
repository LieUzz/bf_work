package com.example.onlinequiz.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.onlinequiz.domain.Quiz;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizRowMapper implements RowMapper<Quiz> {

    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz();
        quiz.setId(rs.getInt("id"));
        quiz.setQuizType(rs.getString("quiztype"));
        return quiz;
    }

}