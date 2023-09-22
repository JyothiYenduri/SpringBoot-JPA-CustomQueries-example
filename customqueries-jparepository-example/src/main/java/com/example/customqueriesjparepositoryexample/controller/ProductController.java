package com.example.customqueriesjparepositoryexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customqueriesjparepositoryexample.entity.Product;
import com.example.customqueriesjparepositoryexample.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @PostMapping("/insert")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        Product insertedProduct = productService.insertProduct(product);
        return new ResponseEntity<>(insertedProduct, HttpStatus.CREATED);
    }


    @GetMapping("/cheaper-than/{maxPrice}")
    public ResponseEntity<List<Product>> findProductsCheaperThan(@PathVariable double maxPrice) {
        List<Product> products = productService.findProductsByPriceLessThan(maxPrice);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/name-containing/{keyword}")
    public ResponseEntity<List<Product>> findProductsByNameContaining(@PathVariable String keyword) {
        List<Product> products = productService.findProductsByNameContaining(keyword);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/more-expensive-than/{minPrice}")
    public ResponseEntity<List<Product>> findProductsMoreExpensiveThan(@PathVariable double minPrice) {
        List<Product> products = productService.findProductsByPriceGreaterThan(minPrice);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
