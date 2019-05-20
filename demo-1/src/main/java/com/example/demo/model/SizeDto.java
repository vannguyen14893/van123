package com.example.demo.model;

import java.util.List;

import com.example.demo.entity.Size;

public class SizeDto {
	private Integer id;

	private String sizeName;
	private Integer total;
	private List<String> nameColors;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	

	public List<String> getNameColors() {
		return nameColors;
	}

	public void setNameColors(List<String> nameColors) {
		this.nameColors = nameColors;
	}

	public SizeDto(Size size) {
		this.id = size.getId();
		this.sizeName = size.getSizeName();
		this.total = size.getTotal();
		
	}

	public SizeDto() {
		super();
	}


}
