package com.example.quizweb.dao;

import com.example.quizweb.domain.Category;
import com.example.quizweb.mapper.CategoryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CategoryDao {

    public List<Category> getAllCategories();

    public void createNewCategory(Category category);

    public Category getCategoryById(Integer id);
}

