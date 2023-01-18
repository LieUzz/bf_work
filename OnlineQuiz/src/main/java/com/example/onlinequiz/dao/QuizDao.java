package com.example.onlinequiz.dao;


import com.example.onlinequiz.domain.Quiz;
import com.example.onlinequiz.mapper.QuizRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizDao {

    JdbcTemplate jdbcTemplate;
    QuizRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizDao(JdbcTemplate jdbcTemplate, QuizRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Quiz getQuizById(Integer qid){
        String query = "SELECT * FROM Quiz WHERE qid = ?";
        List<Quiz> quiz =  jdbcTemplate.query(query, rowMapper, qid);
        return quiz.size() == 0 ? null : quiz.get(0);
    }

    public Quiz getQuizByQuizType(String quiztype){
        String query = "SELECT * FROM Quiz WHERE quiztype = ?";
        List<Quiz> quiz =  jdbcTemplate.query(query, rowMapper, quiztype);
        return quiz.size() == 0 ? null : quiz.get(0);
    }
}
