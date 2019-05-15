package com.example.demo.service.impl.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.model.UserDto;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.utils.UserFilterKeyword;

@Service
@Transactional
public class UserServiceImplDto {
	@Autowired
	private UserServiceImpl impl;

	public List<UserDto> getAll(UserFilterKeyword filter) {
		List<User> users = impl.fillAll(filter);
		List<UserDto> dtos = users.stream().map(UserDto::new).collect(Collectors.toList());
		return dtos;
	}
}
