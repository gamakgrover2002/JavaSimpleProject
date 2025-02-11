package com.example.JavaProject;

import com.example.JavaProject.Controller.ProductController;
import com.example.JavaProject.Entity.Product;
import com.example.JavaProject.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        List<Product> mockProducts = List.of(
                new Product(1L, "Laptop", "Gaming Laptop", 1200.99, 5),
                new Product(2L, "Phone", "Latest smartphone", 799.49, 10)
        );
        when(productService.getAllProducts()).thenReturn(mockProducts);
        List<Product> products = productController.getAllProducts();
        assertNotNull(products, "Product list should not be null");
        assertEquals(2, products.size(), "Product list size should be 2");
        assertEquals("Laptop", products.get(0).getName());
    }

    @Test
    void getProductByIdAndProductExist() {
        Long id = 1L;
        Product mockProduct = new Product(id, "Laptop", "Gaming Laptop", 1200.99, 5);
        when(productService.getProductById(id)).thenReturn(mockProduct);
        Product product = productController.getProductById(id);
        assertNotNull(product, "Product should not be null");
    }
    @Test
    void getProductByIdAndCorrectProductReceived(){
        Long id = 1L;
        Product mockProduct = new Product(id, "Laptop", "Gaming Laptop", 1200.99, 5);
        when(productService.getProductById(id)).thenReturn(mockProduct);
        Product product = productController.getProductById(id);
        assertEquals(id, product.getId(), "Product ID should match");
        assertEquals("Laptop", product.getName(), "Product name should match");
    }

    @Test
    void deletedProductIsDeleted(){
        Long id = 1L;
        Product mockProduct = new Product(id, "Laptop", "Gaming Laptop", 1200.99, 5);
         productService.createProduct(mockProduct);
         productService.deleteProduct(id);
         Product product = productService.getProductById(id);
        assertNull(product);
    }




}
