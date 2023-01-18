package com.example.quizweb.service;

import com.example.quizweb.dao.CategoryDao;
import com.example.quizweb.domain.Category;
import com.example.quizweb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryDao categoryJdbcDao;

    private final CategoryDao categoryHibernateDao;

    @Autowired
    public CategoryService(CategoryDao categoryJdbcDao, CategoryDao categoryHibernateDao) {
        this.categoryJdbcDao = categoryJdbcDao;
        this.categoryHibernateDao = categoryHibernateDao;
    }

    public void createNewCategory(Category category) {
        categoryJdbcDao.createNewCategory(category);
    }

    public List<Category> getAllCategories() {
        return categoryJdbcDao.getAllCategories();
    }

    public Category getCategoryById(Integer id) {
        return categoryJdbcDao.getAllCategories().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(new Category(-1, "no such category"));
    }

    public Optional<Category> validateCategoryById(Integer id) {
        return categoryJdbcDao.getAllCategories().stream()
                .filter((a -> a.getId() == id))
                .findAny();

    }

    @Transactional
    public Category getCategoryByIdHibernate(int id) {
        return categoryHibernateDao.getCategoryById(id);
    }




}
