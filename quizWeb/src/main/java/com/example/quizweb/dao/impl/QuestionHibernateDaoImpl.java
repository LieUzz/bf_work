package com.example.quizweb.dao.impl;


import com.example.quizweb.dao.AbstractHibernateDAO;
import com.example.quizweb.dao.QuestionDao;
import com.example.quizweb.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("questionHibernateDao")
public class QuestionHibernateDaoImpl extends AbstractHibernateDAO<QuestionHibernate> implements QuestionDao {
    public QuestionHibernateDaoImpl() {
        setClazz(QuestionHibernate.class);

    }

    @Override
    public List<QuestionHibernate> getAllQuestions() {
        return loadAllData();
    }

//    @Override
//    public QuestionHibernate findById(Integer id) {
//        return super.findById(id);
//    }

    @Override
    public List<Question> getAllQuestionByCategoryId(Category category) {
        return null;
    }

    @Override
    public Question getQuestionById(Integer id) {
        QuestionHibernate question = findById(id);
        System.out.println("******" + question.toString());

        return new Question(question.getId(), question.getCat_id(), question.getDescription(), null, -1, "", "");
    }
}
