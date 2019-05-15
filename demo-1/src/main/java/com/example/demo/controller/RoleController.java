package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Role;
import com.example.demo.service.impl.RoleServiceImpl;

@RestController
public class RoleController {
	@Autowired
	private RoleServiceImpl impl;

	@GetMapping(value = "/roles/{id}")
	public List<Role> fillAll(@PathVariable("id") Integer id) {
		return impl.fillAll(id);
	}

	@GetMapping(value = "/roles")
	public List<Role> fillAll() {
		return impl.getAll();
	}

	@PostMapping(value = "/delete/role/{id}")

	public ResponseEntity<String> delete(@PathVariable("id") Integer id, @RequestBody Role role) {
		if (impl.check(id, role.getRoleId())) {
			return new ResponseEntity<String>("okkkkk", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Noooooo", HttpStatus.OK);
		}

	}
}