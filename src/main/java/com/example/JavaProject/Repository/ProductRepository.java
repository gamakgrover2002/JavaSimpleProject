package com.example.JavaProject.Repository;

import com.example.JavaProject.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
