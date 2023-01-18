package com.example.quizweb.dao.impl;

import com.example.quizweb.domain.Choice;
import com.example.quizweb.domain.Question;
import com.example.quizweb.dao.ChoiceDao;
import com.example.quizweb.domain.QuestionHibernate;
import com.example.quizweb.mapper.ChoiceRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("choiceJdbcDao")
public class ChoiceJdbcDaoImpl implements ChoiceDao{

    JdbcTemplate jdbcTemplate;
    ChoiceRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ChoiceJdbcDaoImpl(JdbcTemplate jdbcTemplate, ChoiceRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Choice> getAllChoiceByQuestionId(Question question) {
        String query = "SELECT * FROM Choice WHERE question_id = ?";
        List<Choice> choices =  jdbcTemplate.query(query, rowMapper, question.getId());
        return choices.size() == 0 ? null : choices;
    }

    @Override
    public List<Choice> getAllChoiceByQuestionId(QuestionHibernate question) {
        return null;
    }

    @Override
    public Integer getCorrectChoice(List<Choice> choices) {
        int correctId = -1;
        for (Choice choice : choices) {
            if (choice.is_correct()) {
                correctId = choice.getId();
            }
        }
        return correctId;
    }

    @Override
    public Choice getChoiceById(Integer id) {
        String query = "SELECT * FROM Choice WHERE id = (?)";
        List<Choice> choices =  jdbcTemplate.query(query, rowMapper, id);
        return choices.size() == 0 ? null : choices.get(0);
    }

    @Override
    public List<Choice> getAllChoice() {
        return null;
    }
}

