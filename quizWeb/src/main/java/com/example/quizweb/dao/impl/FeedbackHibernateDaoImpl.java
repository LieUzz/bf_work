package com.example.quizweb.dao.impl;


import com.example.quizweb.dao.AbstractHibernateDAO;
import com.example.quizweb.dao.FeedbackDao;
import com.example.quizweb.domain.Feedback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("feedbackHibernateDao")
public class FeedbackHibernateDaoImpl extends AbstractHibernateDAO<Feedback> implements FeedbackDao {

    public FeedbackHibernateDaoImpl() {
        setClazz(Feedback.class);

    }
    @Override
    public List<Feedback> getAllFeedback() {
        return loadAllData();
    }

    @Override
    public void createNewFeedback(Feedback feedback) {
    }
}
