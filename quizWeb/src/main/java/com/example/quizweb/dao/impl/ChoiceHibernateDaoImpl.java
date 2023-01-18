package com.example.quizweb.dao.impl;


import com.example.quizweb.dao.AbstractHibernateDAO;
import com.example.quizweb.dao.ChoiceDao;
import com.example.quizweb.domain.Choice;
import com.example.quizweb.domain.Question;
import com.example.quizweb.domain.QuestionHibernate;
import com.example.quizweb.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("choiceHibernateDao")
public class ChoiceHibernateDaoImpl extends AbstractHibernateDAO<Choice> implements ChoiceDao {

    public ChoiceHibernateDaoImpl() {
        setClazz(Choice.class);

    }

    @Override
    public List<Choice> getAllChoice() {
        return loadAllData();
    }

    @Override
    public List<Choice> getAllChoiceByQuestionId(QuestionHibernate question) {
//        System.out.println("&&&&&&" + question.toString());
//        System.out.println(findById(1).toString());
//        System.out.println(loadAllData());
        return loadAllData().stream().filter(a -> a.getQuestion_id() == question.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Choice> getAllChoiceByQuestionId(Question question) {
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
        if (id != null)
            return findById(id);
        else
            return new Choice(-1, -1, "", false);
    }
}
