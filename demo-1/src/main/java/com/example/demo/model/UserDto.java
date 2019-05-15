package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class UserDto {
	private Integer userId;
	private String userName;
	private String password;

	private String fullName;

	private String email;

	private String phone;

	private int status;
	private String avatar;

	private List<RoleDto> roles = new ArrayList<RoleDto>();
	@JsonIgnoreProperties("userDtos")
	private List<GroupDto> groups = new ArrayList<GroupDto>();

	private List<PostDto> posts = new ArrayList<PostDto>();

	private List<PostCommentDto> postComments = new ArrayList<PostCommentDto>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<GroupDto> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDto> groups) {
		this.groups = groups;
	}

	public List<PostDto> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDto> posts) {
		this.posts = posts;
	}

	public List<PostCommentDto> getPostComments() {
		return postComments;
	}

	public void setPostComments(List<PostCommentDto> postComments) {
		this.postComments = postComments;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public UserDto(User user) {
		this.userId = user.getUserId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.status = user.getStatus();
		this.avatar = user.getAvatar();
		this.groups = user.getGroups().stream().map(GroupDto::new).collect(Collectors.toList());
		this.posts = user.getPosts().stream().map(PostDto::new).collect(Collectors.toList());
		this.postComments = user.getPostComments().stream().map(PostCommentDto::new).collect(Collectors.toList());
		this.roles = user.getRoles().stream().map(RoleDto::new).collect(Collectors.toList());
	}

	public UserDto() {
		super();
	}

}
