package com.example.quizweb.mapper;

import org.springframework.jdbc.core.RowMapper;
import com.example.quizweb.domain.Category;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CategoryRowMapper implements RowMapper<Category> {


    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setCategory_name(rs.getString("cat_name"));
        return category;
    }

}