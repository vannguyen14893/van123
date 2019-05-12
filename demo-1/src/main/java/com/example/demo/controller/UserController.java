package com.example.demo.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.entity.User;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.utils.Contans;

@RestController
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping(value = "/user")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		userServiceImpl.addUser(user);
		return new ResponseEntity<String>(Contans.ADD_SUCCESS_USER, HttpStatus.OK);
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
	@PostMapping(value = "/user/{userId}")
	public void updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
		userServiceImpl.updateUser(user);
	}
	@PostMapping(value = "/user/role/{userId}")
	public void addUserRole(@PathVariable("userId") Integer userId, @RequestBody Integer[] roleId) {
		userServiceImpl.addUserRole(userId, roleId);
	}
	@PostMapping(value = "/user/list-role")
	public void addUserListRole(@RequestBody User user) {
		userServiceImpl.addUserListRole(user);
	}

	@PostMapping(value = "/upload")
	public ResponseEntity<Object> upload(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
		try {
			StringBuilder builder=new StringBuilder();
			for (MultipartFile file : files) {
				String fileName=file.getOriginalFilename();
				builder.append(fileName);
				String path = request.getServletContext().getRealPath("") + Contans.UPLOAD_PATH + File.separator
						+ builder;
				//Contans.saveFile(file.getInputStream(), path);
			}
			return new ResponseEntity<Object>(builder, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NO_CONTENT);
		}
	}

}
