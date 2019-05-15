package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.Role;

public class RoleDto {
	private Integer roleId;
	private String name;
	private List<PermissionDto> permissions = new ArrayList<PermissionDto>();
	private List<MenuDto> menus = new ArrayList<MenuDto>();

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MenuDto> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuDto> menus) {
		this.menus = menus;
	}

	public List<PermissionDto> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<PermissionDto> permissions) {
		this.permissions = permissions;
	}

	public RoleDto(Role role) {
		this.roleId = role.getRoleId();
		this.name = role.getName();
		this.menus = role.getMenus().stream().map(MenuDto::new).collect(Collectors.toList());
		this.permissions = role.getPermissions().stream().map(PermissionDto::new).collect(Collectors.toList());
	}

	public RoleDto() {
		super();
	}

}
