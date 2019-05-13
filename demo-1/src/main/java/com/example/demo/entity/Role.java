package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;
	private String name;
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("roles")
	private List<User> users=new ArrayList<User>();
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("roles")
	private List<Permission> permissions=new ArrayList<Permission>();
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Role(Integer roleId, String name) {
		super();
		this.roleId = roleId;
		this.name = name;
	}

	public Role() {
		super();
	}
	
	

}
