package com.example.shoppingapp.dao;

import com.example.shoppingapp.dao.AbstractHibernateDAO;
import com.example.shoppingapp.entity.User;
import com.example.shoppingapp.entity.util.UserResult;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("userDao")
public class UserDao extends AbstractHibernateDAO<User> {

    public UserDao() {
        setClazz(User.class);
    }

    public User getUserById(Integer id) {
        return findById(id);
    }

    public List<UserResult> getAllUsers() {
        List<UserResult> userResults = new ArrayList<>();
        loadAllData().forEach(user -> userResults.add(UserResult.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build()));
        return userResults;
    }

    public List<UserResult> getAllUsersForAsync(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<UserResult> userResults = new ArrayList<>();
        loadAllData().forEach(user -> userResults.add(UserResult.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .build()));
        return userResults;

    }

    public void createNewUser(User user) {
        createData(user);
    }

//    public Optional<User> loadUserByUsername(String username) {
//        return loadAllData()
//                .stream()
//                .filter(a->a.getUsername().equals(username))
//                .findAny();
//    }
//
//    public Optional<User> loadUserByEmail(String email) {
//        return loadAllData()
//                .stream()
//                .filter(a->a.getEmail().equals(email))
//                .findAny();
//    }
}
