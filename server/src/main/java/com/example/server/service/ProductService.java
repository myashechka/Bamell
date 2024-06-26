package com.example.server.service;

import com.example.server.entity.Product;
import com.example.server.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public Product getProduct(Integer productId){
        return productRepository.findById(productId).orElseThrow();
    }

    public List<Product> getAllByVisibleProduct(Boolean visible){
        return productRepository.getAllByVisible(visible);
    }
}
