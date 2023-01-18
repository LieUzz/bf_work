package com.example.quizweb.dao;

import com.example.quizweb.domain.Choice;
import com.example.quizweb.domain.Question;
import com.example.quizweb.domain.QuestionHibernate;
import com.example.quizweb.mapper.ChoiceRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ChoiceDao {

    public List<Choice> getAllChoiceByQuestionId(Question question);

    public List<Choice> getAllChoiceByQuestionId(QuestionHibernate question);

    public Integer getCorrectChoice(List<Choice> choices);

    public Choice getChoiceById(Integer id);

    public List<Choice> getAllChoice();
}

