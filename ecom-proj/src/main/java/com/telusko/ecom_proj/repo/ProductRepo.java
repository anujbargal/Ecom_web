package com.telusko.ecom_proj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.telusko.ecom_proj.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {
	@Query("SELECT p FROM Product p WHERE " +
	           "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(p.desc) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchproducts(@Param("keyword") String keyword);
}
