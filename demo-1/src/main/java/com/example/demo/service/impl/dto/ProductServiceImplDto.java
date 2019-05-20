package com.example.demo.service.impl.dto;

import java.util.ArrayList;
import java.util.Arrays;
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
		List<ProductDto> productDtos = products.stream().map(product -> {
            ProductDto productDto=new ProductDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setPrice(product.getPrice());
            productDto.setTotal(product.getTotal());
            productDto.setStartDate(product.getStartDate());
            productDto.setCreateDate(product.getCreateDate());
            productDto.setEndDate(product.getEndDate());
            List<String> sizes=new ArrayList<String>();
            List<String> colors=new ArrayList<String>();   
            product.getSizes().forEach(size->sizes.addAll(Arrays.asList(size.getSizeName())));
            product.getSizes().forEach(size->size.getColors().forEach(color->colors.addAll(Arrays.asList(color.getNameColor()))));
            sizes.addAll(colors);
            productDto.setSizes(sizes);
            return productDto;
        }).collect(Collectors.toList());
		return productDtos;

	}
}
