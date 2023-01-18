package com.example.onlinequiz.mapper;

import com.example.onlinequiz.domain.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setQid(rs.getInt("qid"));
        question.setQuestion(rs.getString("question"));
        question.setOpt1(rs.getString("opt1"));
        question.setOpt2(rs.getString("opt2"));
        question.setOpt3(rs.getString("opt3"));
        question.setOpt4(rs.getString("opt4"));
        question.setCorrect(rs.getString("correct"));
        return question;
    }

}
