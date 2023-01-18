package com.example.quizweb.dao.impl;

import com.example.quizweb.dao.FeedbackDao;
import com.example.quizweb.domain.Feedback;
import com.example.quizweb.mapper.FeedbackRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("feedbackJdbcDao")
public class FeedbackJdbcDaoImpl implements FeedbackDao {

    JdbcTemplate jdbcTemplate;
    FeedbackRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public FeedbackJdbcDaoImpl(JdbcTemplate jdbcTemplate, FeedbackRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Feedback> getAllFeedback() {
        String query = "SELECT * FROM Feedback";
        List<Feedback> feedbacks =  jdbcTemplate.query(query, rowMapper);
        return feedbacks.size() == 0 ? null : feedbacks;
    }

    @Override
    public void createNewFeedback(Feedback feedback) {
        String query = "INSERT INTO Feedback (star_rating, feedback_description) values (?, ?)";
        jdbcTemplate.update(query, feedback.getStar_rating(), feedback.getFeedback_description());
    }
}

