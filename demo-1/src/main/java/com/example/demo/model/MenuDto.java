package com.example.demo.model;

import javax.persistence.Column;

import com.example.demo.entity.Menu;

public class MenuDto {
	private Integer id;
	private String name;
	private String link;

	private Integer parentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public MenuDto(Menu menu) {
		this.id = menu.getId();
		this.name = menu.getName();
		this.link = menu.getLink();
		this.parentId = menu.getParentId();
	}

	public MenuDto() {
		super();
	}

}
