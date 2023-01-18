package com.example.quizweb.dao;

import com.example.quizweb.domain.Category;
import com.example.quizweb.domain.Question;
import com.example.quizweb.domain.QuestionHibernate;
import com.example.quizweb.mapper.QuestionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

public interface QuestionDao {

    public List<Question> getAllQuestionByCategoryId(Category category);

    public Question getQuestionById(Integer id);

    public List<QuestionHibernate> getAllQuestions();
}

