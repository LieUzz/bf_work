package com.example.quizweb.dao.impl;

import com.example.quizweb.dao.AbstractHibernateDAO;
import com.example.quizweb.dao.CategoryDao;
import com.example.quizweb.domain.Category;
import com.example.quizweb.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryHibernateDao")
public class CategoryHibernateDaoImpl extends AbstractHibernateDAO<Category> implements CategoryDao {

    public CategoryHibernateDaoImpl() {
        setClazz(Category.class);

    }

    @Override
    public Category getCategoryById(Integer id) {
        return findById(id);
    }
    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public void createNewCategory(Category category) {

    }
}
