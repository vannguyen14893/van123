package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl {
	@Autowired
	private RoleRepository roleRepository;

	public List<Role> fillAll(Integer id) {
		return roleRepository.findByPermissions_Id(id);
	}

	public Boolean check(Integer id, Integer roleId) {
		List<Role> roles = roleRepository.findByPermissions_Id(id);
		for (Role role : roles) {
			if (!(role.getRoleId() == roleId)) {
				continue;
			}
			return true;
		}
		return false;
	}
}
