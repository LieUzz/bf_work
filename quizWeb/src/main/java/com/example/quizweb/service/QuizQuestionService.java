package com.example.quizweb.service;

import com.example.quizweb.dao.QuizQuestionDao;
import com.example.quizweb.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizQuestionService {
    private final QuizQuestionDao quizQuestionJdbcDao;
    private final QuizQuestionDao quizQuestionHibernateDao;
    private final QuestionService questionService;

    @Autowired
    public QuizQuestionService(QuizQuestionDao quizQuestionJdbcDao,
                               QuestionService questionService,
                               QuizQuestionDao quizQuestionHibernateDao) {
        this.quizQuestionJdbcDao = quizQuestionJdbcDao;
        this.quizQuestionHibernateDao = quizQuestionHibernateDao;
        this.questionService = questionService;
    }


    public void createQuizQuestion(List<QuizQuestion> quizQuestions) {

        for (QuizQuestion q : quizQuestions) {
            quizQuestionJdbcDao.createQuizQuestions(q);
        }
    }

    public Optional<QuizQuestion> checkUserChoices(List<QuizQuestion> quizQuestions) {
        return quizQuestions.stream().filter(a -> a.getUser_choice_id() == null).findAny();
    }

    public List<QuizQuestion> createQuizQuestionObject(List<Question> questions, Quiz quiz) {
        List<QuizQuestion> quizQuestionList = new ArrayList<>();

        for (Question q: questions) {
            QuizQuestion tmp = new QuizQuestion();
            tmp.setQuestion_id(q.getId());
            tmp.setQuiz_id(quiz.getId());
            quizQuestionList.add(tmp);
        }
        return quizQuestionList;
    }

    public List<Question> getQuestionsByQuiz(List<QuizQuestion> quizQuestionList) {
        List<Question> questionList = new ArrayList<>();
        for (QuizQuestion q : quizQuestionList) {
            questionList.add(questionService.getQuestionById(q.getQuestion_id()));
        }
        return questionList;

    }

    public List<QuizQuestion> getAllQuizQuestion(Quiz quiz) {
        return quizQuestionJdbcDao.getAllQuizQuestions(quiz);
    }

    @Transactional
    public void getAllQuizQuestionHibernate() {
        Quiz quiz = new Quiz();
        quiz.setId(10);
        List<QuizQuestionHibernate> quizQuestion = quizQuestionHibernateDao.getAllQuizQuestionsHibernate(quiz);

        for (QuizQuestionHibernate u : quizQuestion)
            System.out.println(u.toString());
    }
}
