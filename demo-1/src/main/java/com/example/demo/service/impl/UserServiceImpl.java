package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Group;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private GroupRepository groupRepository;

	public List<User> getAll(Integer roleId) {
		return userRepository.findByRoles_RoleId(roleId);
	}

	public List<User> getAllByGroup(Integer groupId) {
		return userRepository.findByRoles_RoleId(groupId);
	}

	public void addUser(User user) {
		user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		userRepository.save(user);
	}

	public void addUserListRole(User user) {
		List<Role> roles = new ArrayList<Role>();
		for (int i = 0; i < user.getRoleId().length; i++) {
			roles.add(roleRepository.getOne(user.getRoleId()[i]));
		}
		List<Group> groups = new ArrayList<Group>();
		for (int i = 0; i < user.getGroupId().length; i++) {
			groups.add(groupRepository.getOne(user.getGroupId()[i]));
		}
		user.setGroups(groups);
		user.setRoles(roles);
		userRepository.save(user);
	}

	public void updateUser(User user2) {
		User user = userRepository.findById(user2.getUserId()).get();
		if (user != null) {
			user.setUserId(user2.getUserId());
			user.setEmail(user2.getEmail());
			user.setFullName(user2.getFullName());
			user.setPassword(user2.getPassword());
			user.setPhone(user2.getPhone());
			user.setUserName(user2.getUserName());
			user.setStatus(user2.getStatus());
			user.getRoles().clear();
			user.getGroups().clear();
			List<Role> roles = user2.getRoles();
			for (int i = 0; i < user2.getRoleId().length; i++) {
				roles.add(roleRepository.getOne(user2.getRoleId()[i]));
			}
			List<Group> groups = user2.getGroups();
			for (int i = 0; i < user2.getGroupId().length; i++) {
				groups.add(groupRepository.getOne(user2.getGroupId()[i]));
			}
			user.getRoles().addAll(roles);
			user.getGroups().addAll(groups);
			userRepository.save(user);
		}
	}

	public void addUserRole(Integer userId, Integer[] roleId) {
		User user = userRepository.findById(userId).get();
		if (user != null) {
			List<Role> roles = new ArrayList<Role>();
			for (int i = 0; i < roleId.length; i++) {
				roles.add(roleRepository.getOne(roleId[i]));
			}
			user.setRoles(roles);
			userRepository.save(user);
		}
	}

	public void addUserGroup(Integer userId, Integer[] groupId) {
		User user = userRepository.findById(userId).get();
		if (user != null) {
			List<Group> groups = new ArrayList<Group>();
			for (int i = 0; i < groupId.length; i++) {
				groups.add(groupRepository.getOne(groupId[i]));
			}
			user.setGroups(groups);
			userRepository.save(user);
		}
	}
}
