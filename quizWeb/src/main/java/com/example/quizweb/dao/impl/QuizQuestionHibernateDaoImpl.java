package com.example.quizweb.dao.impl;

import com.example.quizweb.dao.AbstractHibernateDAO;
import com.example.quizweb.dao.QuizQuestionDao;
import com.example.quizweb.domain.Quiz;
import com.example.quizweb.domain.QuizQuestion;
import com.example.quizweb.domain.QuizQuestionHibernate;
import com.example.quizweb.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("quizQuestionHibernateDao")
public class QuizQuestionHibernateDaoImpl extends AbstractHibernateDAO<QuizQuestionHibernate> implements QuizQuestionDao {
    public QuizQuestionHibernateDaoImpl() {
        setClazz(QuizQuestionHibernate.class);

    }

    @Override
    public List<QuizQuestion> getAllQuizQuestions(Quiz quiz) {
        return null;
    }

    @Override
    public List<QuizQuestionHibernate> loadAllData() {
        return super.loadAllData();
    }

    @Override
    public List<QuizQuestionHibernate> getAllQuizQuestionsHibernate(Quiz quiz) {
        return loadAllData().stream().filter(a -> a.getQuiz_id() == quiz.getId()).collect(Collectors.toList());
    }

    @Override
    public void createQuizQuestions(QuizQuestion quizQuestion) {

    }

}
