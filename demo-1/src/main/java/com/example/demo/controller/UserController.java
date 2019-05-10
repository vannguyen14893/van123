package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping(value = "/user")
	public void addUser(@RequestBody User user) {
		userServiceImpl.addUser(user);
	}

	@GetMapping(value = "/user/role/{roleId}")
	public List<User> getAll(@PathVariable("roleId") Integer roleId) {
		return userServiceImpl.getAll(roleId);
	}

	@GetMapping(value = "/user/group/{groupId}")
	public List<User> getAllByGroup(@PathVariable("groupId") Integer groupId) {
		return userServiceImpl.getAll(groupId);
	}

	@PostMapping(value = "/user/group/{userId}")
	public void addUserGroup(@PathVariable("userId") Integer userId, @RequestBody Integer[] groupId) {
		userServiceImpl.addUserGroup(userId, groupId);
	}

	@PostMapping(value = "/user/role/{userId}")
	public void addUserRole(@PathVariable("userId") Integer userId, @RequestBody Integer[] roleId) {
		userServiceImpl.addUserRole(userId, roleId);
	}
}
