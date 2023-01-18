package com.example.shoppingapp.service;

import com.example.shoppingapp.dao.ProductDao;
import com.example.shoppingapp.entity.Product;
import com.example.shoppingapp.service.entityService.ProductService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Disabled
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    //    @Spy
    @Mock
    ProductDao productDao;

    Product mockProduct;

    Product mockProduct2;

    List<Product> mockProducts = new ArrayList<>();
    List<Product> mockProductsWithoutOutOfStock = new ArrayList<>();


    @BeforeEach
    public void setup() {
        mockProduct = Product.builder()
                .id(1)
                .name("testProduct")
                .description("testDescription")
                .price(9.99f)
                .quantity(9)
                .build();
        mockProduct2 = Product.builder()
                .id(2)
                .name("testProduct2")
                .description("testDescription2")
                .price(2.22f)
                .quantity(0)
                .build();
        mockProducts.add(mockProduct);
        mockProducts.add(mockProduct2);
        mockProductsWithoutOutOfStock.add(mockProduct);
    }


    @Test
    @DisplayName("get employee by id success scenario")
    public void testGetProductById_success() {
        when(productDao.getById(1)).thenReturn(mockProduct); //means we do not actual call employeeDAO.getEmployeeById(1)
        Product product = productService.getProductById(1);
        assertEquals(product, mockProduct);
    }

    @Test
    public void testGetAllProducts_success() {
        when(productDao.getAllProducts()).thenReturn(mockProducts);
        List<Product> products = productService.getAllProducts();
        for (int i = 0; i < products.size(); i++)
            assertEquals(mockProducts.get(i), products.get(i));
    }

    @Test
    public void testGetAllProductsWithoutOutStock_success() {
        when(productDao.getAllProducts()).thenReturn(mockProductsWithoutOutOfStock);
        List<Product> products = productService.getAllProductsWithoutOutOfStock();
        for (int i = 0; i < products.size(); i++)
            assertEquals(mockProductsWithoutOutOfStock.get(i), products.get(i));
    }
}
