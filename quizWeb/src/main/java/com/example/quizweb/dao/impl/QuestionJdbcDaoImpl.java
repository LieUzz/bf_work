package com.example.quizweb.dao.impl;

import com.example.quizweb.dao.QuestionDao;
import com.example.quizweb.domain.Category;
import com.example.quizweb.domain.Question;
import com.example.quizweb.domain.QuestionHibernate;
import com.example.quizweb.mapper.QuestionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("questionJdbcDao")
public class QuestionJdbcDaoImpl implements QuestionDao {

    JdbcTemplate jdbcTemplate;
    QuestionRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public QuestionJdbcDaoImpl(JdbcTemplate jdbcTemplate, QuestionRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Question> getAllQuestionByCategoryId(Category category) {
        String query = "SELECT * FROM Question WHERE cat_id = (?)";
        List<Question> questions =  jdbcTemplate.query(query, rowMapper, category.getId());
        return questions.size() == 0 ? null : questions;
    }

    @Override
    public Question getQuestionById(Integer id) {
        String query = "SELECT * FROM Question WHERE id = (?)";
        List<Question> questions =  jdbcTemplate.query(query, rowMapper, id);
        return questions.size() == 0 ? null : questions.get(0);
    }

    @Override
    public List<QuestionHibernate> getAllQuestions() {
        return null;
    }
}

