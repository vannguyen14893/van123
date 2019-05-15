package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.Size;

public class SizeDto {
	private Integer id;

	private String sizeName;
	private Integer total;
	private List<ColorDto> colorDtos = new ArrayList<ColorDto>();

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

	public List<ColorDto> getColorDtos() {
		return colorDtos;
	}

	public void setColorDtos(List<ColorDto> colorDtos) {
		this.colorDtos = colorDtos;
	}

	public SizeDto(Size size) {
		this.id = size.getId();
		this.sizeName = size.getSizeName();
		this.total = size.getTotal();
		this.colorDtos = size.getColors().stream().map(ColorDto::new).collect(Collectors.toList());
	}

	public SizeDto() {
		super();
	}


}
