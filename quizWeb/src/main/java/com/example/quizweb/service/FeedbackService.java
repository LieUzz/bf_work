package com.example.quizweb.service;

import com.example.quizweb.dao.FeedbackDao;
import com.example.quizweb.domain.Feedback;
import com.example.quizweb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {
    private final FeedbackDao feedbackJdbcDao;
    private final FeedbackDao feedbackHibernateDao;

    @Autowired
    public FeedbackService(FeedbackDao feedbackJdbcDao, FeedbackDao feedbackHibernateDao) {
        this.feedbackJdbcDao = feedbackJdbcDao;
        this.feedbackHibernateDao = feedbackHibernateDao;
    }

    public void createNewFeedback(Feedback feedback) {
        feedbackJdbcDao.createNewFeedback(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackJdbcDao.getAllFeedback();
    }

    @Transactional
    public void getAllFeedBackHibernate() {
        List<Feedback> feedbacks = feedbackHibernateDao.getAllFeedback();

        for (Feedback f : feedbacks)
            System.out.println(f.toString());
    }


}
