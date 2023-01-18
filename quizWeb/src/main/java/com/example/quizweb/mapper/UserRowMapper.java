package com.example.quizweb.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.quizweb.domain.User;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setFirstname(rs.getString("firstname"));
        user.setLastname(rs.getString("lastname"));
        user.setAddress(rs.getString("address"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.set_admin(rs.getBoolean("is_admin"));
        return user;
    }

}
