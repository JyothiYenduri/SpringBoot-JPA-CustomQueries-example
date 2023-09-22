package com.example.customqueriesjparepositoryexample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customqueriesjparepositoryexample.entity.Product;
import com.example.customqueriesjparepositoryexample.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findProductsByPriceLessThan(double maxPrice) {
        return productRepository.findProductsByPriceLessThan(maxPrice);
    }

    public List<Product> findProductsByNameContaining(String keyword) {
        return productRepository.findProductsByNameContainingCaseInsensitive(keyword);
    }

    public List<Product> findProductsByPriceGreaterThan(double minPrice) {
        return productRepository.findProductsByPriceGreaterThan(minPrice);
    }
}
