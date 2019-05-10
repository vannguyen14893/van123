package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
}
