package com.example.JavaProject.Controller;


import com.example.JavaProject.Entity.Product;
import com.example.JavaProject.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {

            if (product.getName() == null) {
                return ResponseEntity.badRequest().body("Product name cannot be empty.");
            }

            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.ok(createdProduct);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            Product exsistingProduct = productService.getProductById(id);
            if (exsistingProduct != null) {
                 productService.updateProduct(id, product);

            }
            return ResponseEntity.accepted().body("Product updated");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            if(product!=null) {
                productService.deleteProduct(id);
            }
            return ResponseEntity.accepted().body("Product deleted");
        } catch (Exception e) { // Catching general exception, you can specify a more specific exception type if needed
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}