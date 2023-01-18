package com.example.quizweb.dao.impl;


import com.example.quizweb.dao.QuizQuestionDao;
import com.example.quizweb.domain.Quiz;
import com.example.quizweb.domain.QuizQuestion;
import com.example.quizweb.domain.QuizQuestionHibernate;
import com.example.quizweb.mapper.QuizQuestionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("quizQuestionJdbcDao")
public class QuizQuestionJdbcDaoImpl implements QuizQuestionDao {

    JdbcTemplate jdbcTemplate;
    QuizQuestionRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuizQuestionJdbcDaoImpl(JdbcTemplate jdbcTemplate, QuizQuestionRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<QuizQuestion> getAllQuizQuestions(Quiz quiz) {
        String query = "SELECT * FROM Quiz_question WHERE quiz_id = ?";
        List<QuizQuestion> quizQuestions =  jdbcTemplate.query(query, rowMapper, quiz.getId());
        return quizQuestions.size() == 0 ? null : quizQuestions;
    }

    @Override
    public List<QuizQuestionHibernate> getAllQuizQuestionsHibernate(Quiz quiz) {
        return null;
    }

    @Override
    public void createQuizQuestions(QuizQuestion quizQuestion) {
        String query = "INSERT INTO Quiz_question (quiz_id, question_id, user_choice_id) values (?, ?, ?)";
        jdbcTemplate.update(query, quizQuestion.getQuiz_id(), quizQuestion.getQuestion_id(), quizQuestion.getUser_choice_id());
    }

}
