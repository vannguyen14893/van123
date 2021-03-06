package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.Product;
import com.example.demo.repository.customer.ProductRepositoryCutomer;

public interface ProductRepository
		extends JpaRepository<Product, Integer>, ProductRepositoryCutomer, JpaSpecificationExecutor<Product> {
	public Product findByName(String name);

}
