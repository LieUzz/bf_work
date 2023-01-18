package com.example.quizweb.service;

import com.example.quizweb.dao.UserDao;
import com.example.quizweb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final UserService userService;

    @Autowired
    public LoginService(UserService userService) {this.userService = userService; }

    public Optional<User> validateLogin(String username, String password) {
        return userService.validateLogin(username, password);
    }

    public void registerNewUser(User user) {
        userService.createNewUser(user);
    }

    public Optional<User> validateNewUser(String username) {
        return userService.validateNewUser(username);
    }



}
