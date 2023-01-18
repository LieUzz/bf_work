package com.example.shoppingapp.service.entityService;

import com.example.shoppingapp.dao.UserDao;
import com.example.shoppingapp.entity.User;
import com.example.shoppingapp.entity.util.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Transactional(rollbackFor = { Exception.class })
    public List<UserResult> getAllUsers() {
        return userDao.getAllUsers();
    }
    public List<UserResult> getAllUserForNonAsync() {
        return userDao.getAllUsersForAsync();
    }

    @Async("taskExecutor")
    @Transactional(rollbackFor = { Exception.class })
    public CompletableFuture<List<UserResult>> getAllUserForAsync() {
        return CompletableFuture.completedFuture(userDao.getAllUsersForAsync());
    }
}

