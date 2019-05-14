package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RoleRepository;

@Service
@Transactional
public class PermissionServiceImpl {
	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private RoleRepository roleRepository;

	public void addPermissionByRole(Permission permission) {
		List<Role> roles = new ArrayList<Role>();
		for (int i = 0; i < permission.getRoleId().length; i++) {
			roles.add(roleRepository.getOne(permission.getRoleId()[i]));
		}
		permission.setRoles(roles);
		permissionRepository.save(permission);
	}

	public void editPermissionByRole(Permission permission) {
		Permission permission2 = permissionRepository.getOne(permission.getId());
		if (permission2 != null) {
			permission2.setId(permission.getId());
			permission2.setAction(permission.getAction());
			permission2.setController(permission.getController());
			permission2.setDescription(permission.getDescription());
			permission2.getRoles().clear();
		}
		List<Role> roles = permission.getRoles();
		for (int i = 0; i < permission.getRoleId().length; i++) {
			roles.add(roleRepository.getOne(permission.getRoleId()[i]));
		}
		permission2.setRoles(roles);
		permissionRepository.save(permission);

	}

	public void deletePermissonByRoleId(Permission permission) {
		Permission permission2 = permissionRepository.getOne(permission.getId());
		if (permission != null) {
			List<Role> roles = permission2.getRoles();
			for (Role role : roles) {
				for (int i = 0; i < permission.getRoleId().length; i++) {
					if (permission.getRoleId()[i] == role.getRoleId()) {
						
						permissionRepository.delete(permission2);
						break;
					} else {
						System.out.println("khong duoc");
						break;
					}
				}
			}

		}
	}

}
