package com.example.demo.model;

import com.example.demo.entity.Permission;

public class PermissionDto {
	private Integer id;
	private String name;
	private String controller;
	private String action;
	private String description;

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

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PermissionDto(Permission permission) {
		this.id = permission.getId();
		this.name = permission.getName();
		this.controller = permission.getController();
		this.action = permission.getAction();
		this.description = permission.getDescription();
	}

	public PermissionDto() {
		super();
	}

}
