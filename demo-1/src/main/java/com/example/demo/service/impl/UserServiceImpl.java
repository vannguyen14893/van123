package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	public void addUser(User user) {
		user.getRoles().addAll(user.getRoles());
		for (Role role : user.getRoles()) {
			role.getUsers().add(user);

			userRepository.save(user);
		}
	}
}
