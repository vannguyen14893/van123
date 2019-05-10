package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		List<User> users = new ArrayList<User>();
		User user2 = new User();
		users.add(user2);
		user2.setEmail(user.getEmail());
		user2.setFullName(user.getFullName());
		user2.setPassword(user.getPassword());
		user2.setPhone(user.getPhone());
		user2.setStatus(user.getStatus());
		user2.setUserName(user.getUserName());
		user2.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
		userRepository.save(user2);
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
