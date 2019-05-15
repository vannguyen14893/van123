package com.example.demo.service.impl.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;
import com.example.demo.model.ProductDto;
import com.example.demo.service.impl.ProductServiceImpl;
import com.example.demo.utils.FilterKeyword;

@Service
@Transactional
public class ProductServiceImplDto {
	@Autowired
	private ProductServiceImpl productServiceImpl;

	public List<ProductDto> getAll(FilterKeyword filterKeyword) {
		List<Product> products = productServiceImpl.findProductsByName(filterKeyword);
		List<ProductDto> employeeDtos = products.stream().map(ProductDto::new).collect(Collectors.toList());
		return employeeDtos;

	}
}
