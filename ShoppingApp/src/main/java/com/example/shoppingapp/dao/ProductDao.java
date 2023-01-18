package com.example.shoppingapp.dao;

import com.example.shoppingapp.dao.AbstractHibernateDAO;
import com.example.shoppingapp.entity.Product;
import com.example.shoppingapp.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("productDao")
public class ProductDao extends AbstractHibernateDAO<Product> {

    public ProductDao() {
        setClazz(Product.class);
    }

    public Product getById(Integer id) {
        return findById(id);
    }
    public List<Product> getAllProducts() {
        return loadAllData();
    }

    public void createNewProduct(Product product) {
        createData(product);
    }

    public void updateProduct(Product product) {
        updateData(product);
    }

}
