package com.example.quizweb.dao.impl;


import com.example.quizweb.dao.AbstractHibernateDAO;
import com.example.quizweb.dao.UserDao;
import com.example.quizweb.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository("userHibernateDao")
public class UserHibernateDaoImpl extends AbstractHibernateDAO<User> implements UserDao {

    public UserHibernateDaoImpl() {
        setClazz(User.class);

    }

    @Override
    public User getUserById(Integer id) {
        return findById(id);
    }
    @Override
    public List<User> getAllUsers() {
        return loadAllData();
    }

    public List<User> getUserByIds(List<Integer> ids) {
        return ids.stream().map(this::findById).collect(Collectors.toList());
    }

    @Override
    public void createNewUser(User user) {

    }
}
