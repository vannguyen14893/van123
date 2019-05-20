package com.example.demo.service.impl.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Role;
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
		List<UserDto> userDtos = users.stream().map(user -> {
			UserDto userDto = new UserDto();
            userDto.setUserId(user.getUserId());
            userDto.setEmail(user.getEmail());
            userDto.setFullName(user.getFullName());
            userDto.setUserName(user.getUserName());
            userDto.setAvatar(user.getAvatar());
            userDto.setStatus(user.getStatus());
            userDto.setPassword(user.getPassword());
            List<String> roleName=new ArrayList<String>();
            user.getRoles().forEach(role-> roleName.addAll(Arrays.asList(role.getName())));
            List<String> groupName=new ArrayList<String>();
            user.getGroups().forEach(group->groupName.addAll(Arrays.asList(group.getName())));
            List<String> messages=new ArrayList<String>();
            user.getPosts().forEach(post->messages.addAll(Arrays.asList(post.getMessage())));
            List<String> postCommentMessages=new ArrayList<String>();
            user.getPostComments().forEach(postComment->postCommentMessages.addAll(Arrays.asList(postComment.getMessage())));
            userDto.setGroupName(groupName);
            userDto.setRoleName(roleName);
            userDto.setPostMessage(messages);
            userDto.setPostCommentMessage(postCommentMessages);
			return userDto;
			
		}).collect(Collectors.toList());
		
		return userDtos;
	}
}
