package com.example.quizweb.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.quizweb.domain.Quiz;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizRowMapper implements RowMapper<Quiz> {


    @Override
    public Quiz mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quiz quiz = new Quiz();
        quiz.setId(rs.getInt("id"));
        quiz.setUser_id(rs.getInt("user_id"));
        quiz.setCat_id(rs.getInt("cat_id"));
        quiz.setQuiz_name(rs.getString("quiz_name"));
        quiz.setStart_time(rs.getTimestamp("start_time"));
        quiz.setEnd_time(rs.getTimestamp("end_time"));
        return quiz;
    }

}