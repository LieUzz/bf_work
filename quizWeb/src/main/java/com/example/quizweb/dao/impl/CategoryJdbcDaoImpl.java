package com.example.quizweb.dao.impl;

import com.example.quizweb.dao.CategoryDao;
import com.example.quizweb.domain.Category;
import com.example.quizweb.mapper.CategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryJdbcDao")
public class CategoryJdbcDaoImpl implements CategoryDao {

    JdbcTemplate jdbcTemplate;
    CategoryRowMapper rowMapper;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CategoryJdbcDaoImpl(JdbcTemplate jdbcTemplate, CategoryRowMapper rowMapper, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Category> getAllCategories() {
        String query = "SELECT * FROM Category";
        List<Category> categories =  jdbcTemplate.query(query, rowMapper);
        return categories.size() == 0 ? null : categories;
    }

    @Override
    public void createNewCategory(Category category) {
        String query = "INSERT INTO Category (cat_name) values (?)";
        jdbcTemplate.update(query, category.getCategory_name());
    }

    @Override
    public Category getCategoryById(Integer id) {
        return null;
    }
}

