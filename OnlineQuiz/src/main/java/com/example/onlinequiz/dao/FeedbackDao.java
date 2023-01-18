package com.example.onlinequiz.dao;

import com.example.onlinequiz.domain.Feedback;
import com.example.onlinequiz.mapper.FeedbackRowMapper;
import com.example.onlinequiz.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FeedbackDao {

    JdbcTemplate jdbcTemplate;
    UserRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public FeedbackDao(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void createNewFeedBack(Integer star, String text) {
        String query = "INSERT INTO Feedback (star, fbtext) values (?, ?)";
        jdbcTemplate.update(query, star, text);
    }
}
