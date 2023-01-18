package com.example.onlinequiz.dao;

import com.example.onlinequiz.domain.QuizRecord;
import com.example.onlinequiz.mapper.QuizRecordRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizRecordDao {

    JdbcTemplate jdbcTemplate;
    QuizRecordRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizRecordDao(JdbcTemplate jdbcTemplate, QuizRecordRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public void createNewUser(Integer uid, Integer qid, Integer score, String starttime, String endtime) {
        String query = "INSERT INTO Quizrecord (uid, qid, score, starttime, endtime) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, uid, qid, score, starttime, endtime);
    }


}