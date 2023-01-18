package com.example.quizweb.dao;

import com.example.quizweb.domain.User;
import com.example.quizweb.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserDao {


    public List<User> getAllUsers();

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

    public void createNewUser(User user);

    public User getUserById(Integer id);
}
