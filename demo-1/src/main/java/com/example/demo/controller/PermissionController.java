package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Permission;
import com.example.demo.service.impl.PermissionServiceImpl;

@RestController
public class PermissionController {
	@Autowired
	private PermissionServiceImpl permissionServiceImpl;

	@PostMapping(value = "/permission")
	public void addPermissionByRole(@RequestBody Permission permission) {
		permissionServiceImpl.addPermissionByRole(permission);
	}

	@PostMapping(value = "/permission/{id}")
	public void editPermissionByRole(@PathVariable("id") Integer id, @RequestBody Permission permission) {
		permissionServiceImpl.editPermissionByRole(permission);
	}

	@PostMapping(value = "/permissions")
	public void delete(@RequestBody Permission permission) {
		permissionServiceImpl.deletePermissonByRoleId(permission);
	}
}
