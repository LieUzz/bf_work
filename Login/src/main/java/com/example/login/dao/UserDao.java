package com.example.login.dao;

import com.example.login.dao.AbstractHibernateDAO;
import com.example.login.entity.User;
import com.example.login.exception.InvalidCredentialsException;
import org.springframework.stereotype.Repository;

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
    public List<User> getAllUsers() {
        return loadAllData();
    }

    public List<User> getUserByIds(List<Integer> ids) {
        return ids.stream().map(this::findById).collect(Collectors.toList());
    }

    public void createNewUser(User user) {
        createData(user);
    }

    public Optional<User> loadUserByUsername(String username) {
        return loadAllData()
                .stream()
                .filter(a->a.getUsername().equals(username))
                .findAny();
    }

    public Optional<User> loadUserByEmail(String email) {
        return loadAllData()
                .stream()
                .filter(a->a.getEmail().equals(email))
                .findAny();
    }

//    public User getErrorUser() throws InvalidCredentialsException{
//        throw new InvalidCredentialsException("Incorrect credentials, please try again");
//    }
}
