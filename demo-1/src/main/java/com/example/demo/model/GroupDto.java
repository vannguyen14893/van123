package com.example.demo.model;

import com.example.demo.entity.Group;

public class GroupDto {
	private Integer groupId;
	private String name;
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public GroupDto(Group group) {
		this.groupId = group.getGroupId();
		this.name = group.getName();
	}

	public GroupDto() {
		super();
	}

}
