package com.example.quizweb.dao;

import com.example.quizweb.domain.Category;
import com.example.quizweb.domain.Quiz;
import com.example.quizweb.domain.User;
import com.example.quizweb.mapper.QuizRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public interface QuizDao {


    public List<Quiz> getAllQuiz();

    public List<Quiz> getAllQuizByUser(User user);

    public void createNewQuiz(Quiz quiz);

    public void setEndTimeByQuizId(Quiz quiz);

    public List<Quiz> getAllQuizByCategory(Category category);
}

