package com.example.quizweb.service;

import com.example.quizweb.dao.ChoiceDao;
import com.example.quizweb.dao.QuestionDao;
import com.example.quizweb.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class QuestionService {
    private final QuestionDao questionJdbcDao;

    private final QuestionDao questionHibernateDao;
    private final ChoiceDao choiceJdbcDao;
    private final ChoiceDao choiceHibernateDao;



    @Autowired
    public QuestionService(QuestionDao questionJdbcDao,
                           ChoiceDao choiceJdbcDao,
                           QuestionDao questionHibernateDao,
                           ChoiceDao choiceHibernateDao) {
        this.questionJdbcDao = questionJdbcDao;
        this.questionHibernateDao = questionHibernateDao;
        this.choiceJdbcDao = choiceJdbcDao;
        this.choiceHibernateDao = choiceHibernateDao;
    }

    public List<Question> getAllQuestionByCategoryId(Category category) {
        List<Question> questions = questionJdbcDao.getAllQuestionByCategoryId(category);
        for (Question question : questions) {
            List<Choice> choices = choiceJdbcDao.getAllChoiceByQuestionId(question);
            question.setChoices(choices);
            question.setCorrectChoiceId(choiceJdbcDao.getCorrectChoice(choices));
        }
        return questions;
    }

    public Question getQuestionById(Integer id) {
        Question question = questionJdbcDao.getQuestionById(id);
        List<Choice> choices = choiceJdbcDao.getAllChoiceByQuestionId(question);
        question.setChoices(choices);
        question.setCorrectChoiceId(choiceJdbcDao.getCorrectChoice(choices));
        return question;
    }


    public List<Question> getFiveQuestionByCategoryId(Category category) {
        List<Question> questions = getAllQuestionByCategoryId(category);
        Collections.shuffle(questions);
        return questions.subList(0, 5);

    }

    public Choice getChoiceById(Integer id) {
        return choiceJdbcDao.getChoiceById(id);
    }


    @Transactional
    public List<Question> getAllQuestionHibernate() {
        List<QuestionHibernate> questions = questionHibernateDao.getAllQuestions();

        List<Question> questionList = new ArrayList<>();
        for (QuestionHibernate q : questions) {

            List<Choice> choices = getAllChoiceByQuestionHibernate(q);
            questionList.add(new Question(q.getId(),
                    q.getCat_id(),
                    q.getDescription(),
                    choices,
                    -1,
                    null,
                    null));
        }
        return questionList;
    }

    @Transactional
    public List<Choice> getAllChoiceByQuestionHibernate(QuestionHibernate question) {
        List<Choice> choices = choiceHibernateDao.getAllChoiceByQuestionId(question);

        for (Choice c : choices)
            System.out.println(c.toString());
        return choices;
    }



}
