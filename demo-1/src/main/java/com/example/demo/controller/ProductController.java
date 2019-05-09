package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.impl.ProductServiceImpl;
import com.example.demo.utils.Contans;
import com.example.demo.utils.FilterKeyword;

@RestController
//@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
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
	public ResponseEntity<String> add(@RequestBody Product product) {
//		HttpHeaders responseHeaders = new HttpHeaders();
//		 responseHeaders.set("MyResponseHeader", "MyValue");
		productServiceImpl.addProduct(product);
		// System.out.println(responseHeaders);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

	@PostMapping(value = "/product/{id}")
	public ResponseEntity<String> validateProduct(@PathVariable("id") Integer id, @RequestBody String name) {

		if (productServiceImpl.checkProduct(name) == null) {
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(Contans.ERROR, HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/product")
	public ResponseEntity<String> update(@RequestBody Product product) {
		productServiceImpl.updateProduct(product);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping(value = "/count")
	public Long count() {
		return productServiceImpl.count();
	}
}
