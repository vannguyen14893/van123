package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.model.ProductDto;
import com.example.demo.service.impl.ProductServiceImpl;
import com.example.demo.service.impl.dto.ProductServiceImplDto;
import com.example.demo.utils.Contans;
import com.example.demo.utils.FilterKeyword;

@RestController
//@CrossOrigin(value = "http://localhost:4200", maxAge = 3600)
public class ProductController {
//	@Autowired
//	private ProductService productService;
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private ProductServiceImplDto dto;

	@PostMapping(value = "/products")
	public ResponseEntity<List<ProductDto>> getAll(@RequestBody FilterKeyword filterKeyword) {
		List<ProductDto> products = dto.getAll(filterKeyword);
		return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
	}

	@PostMapping(value = "/product")
	public ResponseEntity<String> add(@RequestBody Product product) {
//		HttpHeaders responseHeaders = new HttpHeaders();
//		 responseHeaders.set("MyResponseHeader", "MyValue");
		productServiceImpl.addProduct(product);
		return new ResponseEntity<String>(Contans.ADD_SUCCESS, HttpStatus.OK);

	}

	@PostMapping(value = "/validate/name")
	public ResponseEntity<String> validateProduct(@RequestBody Product product) {
		if (productServiceImpl.checkProductName(product.getName())) {
			return new ResponseEntity<String>(Contans.ERROR, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>(Contans.YOU_CAN_CHOOSE_NAME, HttpStatus.OK);
		}

	}

	@PostMapping(value = "/validate/date")
	public ResponseEntity<String> validateDate(@RequestBody Product product) {
		if (!productServiceImpl.checkDate(product.getStartDate(), product.getEndDate())) {
			return new ResponseEntity<String>(Contans.ERROR_DATE, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>(HttpStatus.OK);
		}
	}

	@PutMapping(value = "/product")
	public ResponseEntity<String> update(@RequestBody Product product) {
		productServiceImpl.updateProduct(product);
		return new ResponseEntity<String>(Contans.UPDATE_SUCCESS, HttpStatus.OK);

	}

	@GetMapping(value = "/count")
	public Long count() {
		return productServiceImpl.count();
	}

	@PostMapping(value = "/delete")
	public ResponseEntity<String> delete(@RequestBody Product product) {
		productServiceImpl.deleteProduct(product.getId());
		return new ResponseEntity<String>(Contans.DELETE_SUCCESS, HttpStatus.OK);
	}

}
