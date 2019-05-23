package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String link;
	@Column(name = "parent_id")
	private Integer parentId;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "menu_role", joinColumns = { @JoinColumn(name = "menu_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private List<Role> roles = new ArrayList<Role>();
	@OneToMany(mappedBy = "menu",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnoreProperties("menu")
	private List<ContentMenu> contentMenus=new ArrayList<ContentMenu>();
	@Transient
    private Integer [] roleId;
	@Transient
    private Integer  parentId2;
	@Transient
    private Integer  menuId;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer[] getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer[] roleId) {
		this.roleId = roleId;
	}

	public List<ContentMenu> getContentMenus() {
		return contentMenus;
	}

	public void setContentMenus(List<ContentMenu> contentMenus) {
		this.contentMenus = contentMenus;
	}

	public Integer getParentId2() {
		return parentId2;
	}

	public void setParentId2(Integer parentId2) {
		this.parentId2 = parentId2;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

}
