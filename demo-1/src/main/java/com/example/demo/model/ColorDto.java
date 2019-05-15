package com.example.demo.model;

import com.example.demo.entity.Color;

public class ColorDto {
	private Integer id;
	private String nameColor;
	private Integer total;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameColor() {
		return nameColor;
	}

	public void setNameColor(String nameColor) {
		this.nameColor = nameColor;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public ColorDto(Color color) {
		this.id = color.getId();
		this.nameColor = color.getNameColor();
		this.total = color.getTotal();
	}

	public ColorDto() {
		super();
	}

}
