package com.example.shoppingapp.dao;


import com.example.shoppingapp.entity.Permission;
import com.example.shoppingapp.entity.Product;
import com.example.shoppingapp.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(value = "test")
@SpringBootTest
@Disabled
public class ProductDaoTest {

    @Autowired
    ProductDao productDao;

    Product mockProduct;
    Product mockProduct2;

//    Permission mockPermission;

    @BeforeEach
    public void setup() {
        mockProduct = Product.builder()
                .description("descriptionTest")
                .name("productTest")
                .price(9.99f)
                .quantity(10)
                .build();
        mockProduct2 = Product.builder()
                .description("descriptionTest2")
                .name("productTest2")
                .price(2.22f)
                .quantity(20)
                .build();
    }

    @Test
    @Transactional
    public void testGetProductById_found() {
        productDao.createNewProduct(mockProduct);
        Integer id = mockProduct.getId();
        assertNotNull(id);
        assertEquals(mockProduct, productDao.getById(id));
        mockProduct.setId(null);
    }

    @Test
    @Transactional
    public void testUpdateProduct_success() {
        productDao.createNewProduct(mockProduct);
        Integer id = mockProduct.getId();
        assertNotNull(id);
        mockProduct.setQuantity(20);
        productDao.updateProduct(mockProduct);
        assertEquals(mockProduct, productDao.getById(id));
        mockProduct.setId(null);
    }

    @Test
    @Transactional
    public void testLoadAllProduct_success() {
        productDao.createNewProduct(mockProduct);
        productDao.createNewProduct(mockProduct2);
        Integer id = mockProduct.getId();
        Integer id2 = mockProduct2.getId();
        assertNotNull(id);
        assertNotNull(id2);
        List<Product> productList = productDao.getAllProducts();

        assertEquals(mockProduct, productList.get(0));
        assertEquals(mockProduct2, productList.get(1));
        mockProduct.setId(null);
        mockProduct2.setId(null);
    }
}
