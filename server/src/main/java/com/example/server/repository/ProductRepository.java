package com.example.server.repository;

import com.example.server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getAllByVisible(Boolean visible);
}