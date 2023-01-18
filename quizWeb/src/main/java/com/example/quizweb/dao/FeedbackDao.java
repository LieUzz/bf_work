package com.example.quizweb.dao;

import com.example.quizweb.domain.Feedback;
import com.example.quizweb.mapper.FeedbackRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

public interface FeedbackDao {

    public List<Feedback> getAllFeedback();

    public void createNewFeedback(Feedback feedback);
}

