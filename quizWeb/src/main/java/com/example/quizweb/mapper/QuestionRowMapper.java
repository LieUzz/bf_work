package com.example.quizweb.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.quizweb.domain.Question;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {


    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setCat_id(rs.getInt("cat_id"));
        question.setDescription(rs.getString("question_description"));
        return question;
    }

}