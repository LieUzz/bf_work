package com.example.quizweb.dao.impl;

import com.example.quizweb.domain.User;
import com.example.quizweb.dao.UserDao;
import com.example.quizweb.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userJdbcDao")
public class UserJdbcDaoImpl implements UserDao{

    JdbcTemplate jdbcTemplate;
    UserRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserJdbcDaoImpl(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        String query = "SELECT * FROM User";
        List<User> users =  jdbcTemplate.query(query, rowMapper);
        return users.size() == 0 ? null : users;
    }

//    public User getUserById(Integer id){
//        String query = "SELECT * FROM User WHERE id = ?";
//        List<User> users =  jdbcTemplate.query(query, rowMapper, id);
//        return users.size() == 0 ? null : users.get(0);
//    }

//    public User getUser(String name, String password){
//        String query = "SELECT * FROM User WHERE username = ? and password = ?";
//        List<User> users =  jdbcTemplate.query(query, rowMapper, name, password);
//        return users.size() == 0 ? null : users.get(0);
//    }

//    public User getUserByUsername(String name){
//        String query = "SELECT * FROM User WHERE username = ?";
//        List<User> users =  jdbcTemplate.query(query, rowMapper, name);
//        return users.size() == 0 ? null : users.get(0);
//    }
    @Override
    public void createNewUser(User user) {
        String query = "INSERT INTO User " +
                "(username, firstname, lastname, address, email, password, phone, is_admin) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, user.getUsername(), user.getFirstname(), user.getLastname(),
                user.getAddress(), user.getEmail(), user.getPassword(), user.getPhone(), user.is_admin());
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }
}
