package com.example.shoppingapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDAO<T extends Serializable> {

    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<T> clazz;

    protected final void setClazz(final Class<T> clazzToSet) {
        clazz = clazzToSet;
    }

    public T findById(final Integer id) {
        return getCurrentSession().get(clazz, id);
    }

    public List<T> loadAllData() {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        criteria.from(clazz);
        List<T> data = session.createQuery(criteria).getResultList();
//        tx.commit();
        return data;
    }

    public void createData(T data) {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
        session.save(data);
//        tx.commit();
    }

    public void updateData(T data) {
        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.beginTransaction();
        session.merge(data);
//        tx.commit();
    }



    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}



