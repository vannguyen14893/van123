package com.example.demo.repository.customer;

import java.util.List;

import com.example.demo.entity.Product;
import com.example.demo.utils.FilterKeyword;

public interface ProductRepositoryCutomer {
	List<Product> findProductsByName(FilterKeyword filterKeyword);

	
}
