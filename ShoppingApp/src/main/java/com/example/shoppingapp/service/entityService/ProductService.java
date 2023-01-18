package com.example.shoppingapp.service.entityService;

import com.example.shoppingapp.dao.ProductDao;
import com.example.shoppingapp.entity.Product;
import com.example.shoppingapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product getProductById(Integer id) {
        return productDao.getById(id);
    }

    @Cacheable("products")
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public List<Product> getAllProductsWithoutOutOfStock() {
        return productDao.getAllProducts().stream().filter(a->a.getQuantity()>0).collect(Collectors.toList());
    }

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    public void createProduct(Product product) {
        productDao.createNewProduct(product);
    }


}
