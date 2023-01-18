package com.example.quizweb.dao.impl;

import com.example.quizweb.dao.AbstractHibernateDAO;
import com.example.quizweb.dao.QuizDao;
import com.example.quizweb.domain.Category;
import com.example.quizweb.domain.Quiz;
import com.example.quizweb.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("quizHibernateDao")
public class QuizHibernateDaoImpl extends AbstractHibernateDAO<Quiz> implements QuizDao{

    public QuizHibernateDaoImpl() {
        setClazz(Quiz.class);
    }

    @Override
    public List<Quiz> getAllQuiz() {
        return loadAllData();
    }

    @Override
    public List<Quiz> getAllQuizByUser(User user) {
        return loadAllData().stream().filter(quiz -> quiz.getUser_id() == user.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Quiz> getAllQuizByCategory(Category category){
        return loadAllData().stream().filter(quiz -> quiz.getCat_id() == category.getId()).collect(Collectors.toList());
    }

    @Override
    public void createNewQuiz(Quiz quiz) {

    }

    @Override
    public void setEndTimeByQuizId(Quiz quiz) {

    }
}
