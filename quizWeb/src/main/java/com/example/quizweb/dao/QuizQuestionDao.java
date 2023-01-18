package com.example.quizweb.dao;

import com.example.quizweb.domain.Quiz;
import com.example.quizweb.domain.QuizQuestion;
import com.example.quizweb.domain.QuizQuestionHibernate;
import com.example.quizweb.mapper.QuizQuestionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

public interface QuizQuestionDao {

    public List<QuizQuestion> getAllQuizQuestions(Quiz quiz);

    public List<QuizQuestionHibernate> getAllQuizQuestionsHibernate(Quiz quiz);

    public void createQuizQuestions(QuizQuestion quizQuestion);

}
