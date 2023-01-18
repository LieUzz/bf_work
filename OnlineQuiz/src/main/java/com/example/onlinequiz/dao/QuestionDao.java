package com.example.onlinequiz.dao;

import com.example.onlinequiz.domain.Question;
import com.example.onlinequiz.mapper.QuestionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionDao {

    JdbcTemplate jdbcTemplate;
    QuestionRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuestionDao(JdbcTemplate jdbcTemplate, QuestionRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Question> getUserById(Integer qid){
        String query = "SELECT * FROM question WHERE qid = ?";
        List<Question> questions =  jdbcTemplate.query(query, rowMapper, qid);
        return questions.size() == 0 ? null : questions;
    }

}
