package com.example.demo.model;

import java.util.List;

public class UserDto {
	private Integer userId;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phone;
	private int status;
	private String avatar;
    private List<String>roleName;
    private List<String>groupName;
    private List<String>postMessage;
    private List<String>postCommentMessage;
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

	public UserDto() {
		super();
	}

	public List<String> getRoleName() {
		return roleName;
	}

	public void setRoleName(List<String> roleName) {
		this.roleName = roleName;
	}

	public List<String> getGroupName() {
		return groupName;
	}

	public void setGroupName(List<String> groupName) {
		this.groupName = groupName;
	}

	public List<String> getPostMessage() {
		return postMessage;
	}

	public void setPostMessage(List<String> postMessage) {
		this.postMessage = postMessage;
	}

	public List<String> getPostCommentMessage() {
		return postCommentMessage;
	}

	public void setPostCommentMessage(List<String> postCommentMessage) {
		this.postCommentMessage = postCommentMessage;
	}
	
}
