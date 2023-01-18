package com.example.onlinequiz.dao;

import com.example.onlinequiz.domain.User;
import com.example.onlinequiz.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    JdbcTemplate jdbcTemplate;
    UserRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate, UserRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public User getUserById(Integer id){
        String query = "SELECT * FROM User WHERE id = ?";
        List<User> users =  jdbcTemplate.query(query, rowMapper, id);
        return users.size() == 0 ? null : users.get(0);
    }

    public User getUser(String name, String password){
        String query = "SELECT * FROM User WHERE name = ? and password = ?";
        List<User> users =  jdbcTemplate.query(query, rowMapper, name, password);
        return users.size() == 0 ? null : users.get(0);
    }

    public User getUserByName(String name){
        String query = "SELECT * FROM User WHERE name = ?";
        List<User> users =  jdbcTemplate.query(query, rowMapper, name);
        return users.size() == 0 ? null : users.get(0);
    }



    public void createNewUser(String name, String password, String phone, String email) {
        String query = "INSERT INTO User (name, password, phone, email) values (?, ?, ?, ?)";
        jdbcTemplate.update(query, name, password, phone, email);
    }
}
