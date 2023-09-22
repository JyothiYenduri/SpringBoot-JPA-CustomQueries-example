package com.example.customqueriesjparepositoryexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.customqueriesjparepositoryexample.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE p.price < :maxPrice")
    List<Product> findProductsByPriceLessThan(@Param("maxPrice") double maxPrice);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> findProductsByNameContainingCaseInsensitive(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE p.price > :minPrice")
    List<Product> findProductsByPriceGreaterThan(@Param("minPrice") double minPrice);
    
    Product save(Product product);

}
