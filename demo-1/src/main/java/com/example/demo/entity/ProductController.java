package com.example.demo.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.impl.ProductServiceImpl;
import com.example.demo.utils.FilterKeyword;

@RestController
public class ProductController {
//	@Autowired
//	private ProductService productService;
	@Autowired
	private ProductServiceImpl productServiceImpl;

	@PostMapping(value = "/products")
	public List<Product> getAll(@RequestBody FilterKeyword filterKeyword) {

		return productServiceImpl.findProductsByName(filterKeyword);
	}

	@PostMapping(value = "/product")
	public void add(@RequestBody Product product) {
		productServiceImpl.addProduct(product);
	}
	@PutMapping(value = "/product")
	public void update(@RequestBody Product product) {
		productServiceImpl.updateProduct(product);
	}
}
