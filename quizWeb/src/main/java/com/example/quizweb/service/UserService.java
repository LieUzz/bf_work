package com.example.quizweb.service;


import com.example.quizweb.dao.UserDao;
import com.example.quizweb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userJdbcDao;

    private final UserDao userHibernateDao;

    @Autowired
    public UserService(UserDao userJdbcDao, UserDao userHibernateDao) {
        this.userJdbcDao = userJdbcDao;
        this.userHibernateDao = userHibernateDao;
    }

    public void createNewUser(User user) {
        userJdbcDao.createNewUser(user);
    }

    public List<User> getAllUsers() {
        return userJdbcDao.getAllUsers();
    }

    public User getUserById(int id) {
        return userJdbcDao.getAllUsers().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(new User(-1, "invalid username", "invalid password"));
    }

    public Optional<User> validateLogin(String username, String password) {
        return userJdbcDao.getAllUsers().stream()
                .filter(a -> a.getUsername().equals(username)
                        && a.getPassword().equals(password))
                .findAny();
    }

    public Optional<User> validateNewUser(String username) {
        return userJdbcDao.getAllUsers().stream()
                .filter((a -> a.getUsername().equals(username)))
                .findAny();
    }

    @Transactional
    public void getUserByIdHibernate(int id) {
        User user = userHibernateDao.getUserById(id);
        System.out.println("====== "+user.toString());
    }

    @Transactional
    public List<User> getAllUserHibernate() {
        List<User> users = userHibernateDao.getAllUsers();

        for (User u : users)
            System.out.println(u.toString());
        return users;
    }

}