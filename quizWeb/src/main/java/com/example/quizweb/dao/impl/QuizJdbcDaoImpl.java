package com.example.quizweb.dao.impl;

import com.example.quizweb.dao.QuizDao;
import com.example.quizweb.domain.Category;
import com.example.quizweb.domain.Quiz;
import com.example.quizweb.domain.User;
import com.example.quizweb.mapper.QuizRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Repository("quizJdbcDao")
public class QuizJdbcDaoImpl implements QuizDao {

    JdbcTemplate jdbcTemplate;
    QuizRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizJdbcDaoImpl(JdbcTemplate jdbcTemplate, QuizRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Quiz> getAllQuiz() {
        String query = "SELECT * FROM Quiz";
        List<Quiz> quizList =  jdbcTemplate.query(query, rowMapper);
        return quizList.size() == 0 ? null : quizList;
    }

    @Override
    public List<Quiz> getAllQuizByUser(User user) {
        return getAllQuiz().stream().filter(a->a.getUser_id() == user.getId()).collect(Collectors.toList());
    }

    @Override
    public void createNewQuiz(Quiz quiz) {
        String query = "INSERT INTO Quiz (user_id, cat_id, quiz_name, start_time) values (?, ?, ?, ?)";
        jdbcTemplate.update(query, quiz.getUser_id(), quiz.getCat_id(), quiz.getQuiz_name(), quiz.getStart_time());
    }

    @Override
    public void setEndTimeByQuizId(Quiz quiz) {
        String query = "UPDATE Quiz SET end_time = ? WHERE id = ?";
        jdbcTemplate.update(query, quiz.getEnd_time(), quiz.getId());
    }

    @Override
    public List<Quiz> getAllQuizByCategory(Category category) {
        return null;
    }
}

