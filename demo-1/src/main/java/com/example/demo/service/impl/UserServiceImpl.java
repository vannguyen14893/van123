package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Group;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.UserFilterKeyword;

@Service
@Transactional
public class UserServiceImpl {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private GroupRepository groupRepository;
	@PersistenceContext
	EntityManager entityManager;
	public static final String LIST = "select u from User u ";
	public static final String SORT = "ORDER BY ";

	public List<User> fillAll(UserFilterKeyword filter) {
		StringBuilder builder = new StringBuilder(LIST);
		if (StringUtils.isNotBlank(filter.getName())) {
			builder.append("join u.roles r ");
		}
		if (StringUtils.isNotBlank(filter.getNameGroup())) {
			builder.append("join u.groups g ");
		}
		if (StringUtils.isNotBlank(filter.getProductName())) {
			builder.append("join u.posts p join p.product pd ");
		}
		if (StringUtils.isNotBlank(filter.getNameColor())) {
			builder.append("join u.posts p join p.product pd join pd.sizes s join s.colors c ");
		}
		builder.append(" WHERE 1=1 ");
		if (StringUtils.isNotBlank(filter.getUserName())) {
			builder.append(" AND u.userName = '" + filter.getUserName() + "'");
		}
		if (filter.getStatus() != null) {
			builder.append(" AND u.status = '" + filter.getStatus() + "'");
		}
		if (StringUtils.isNotBlank(filter.getName())) {

			builder.append(" AND r.name = '" + filter.getName() + "' ");
		}
		if (StringUtils.isNotBlank(filter.getNameGroup())) {

			builder.append(" AND g.name = '" + filter.getNameGroup() + "' ");
		}
		if (StringUtils.isNotBlank(filter.getProductName())) {

			builder.append(" AND pd.name = '" + filter.getProductName() + "' ");
		}
		if (StringUtils.isNotBlank(filter.getNameColor())) {

			builder.append(" AND c.nameColor = '" + filter.getNameColor() + "' ");
		}
		if (StringUtils.isNotBlank(filter.getFullName())) {
			builder.append(" AND LOWER(u.email) like '%" + filter.getFullName().toLowerCase() + "%' ");
		}
		if (!StringUtils.isNotBlank(filter.getSortName())) {
			builder.append(SORT + "u.userId");
		} else {
			builder.append(SORT + "u." + filter.getSortName() + "");
			builder.append(filter.getSort() ? " ASC" : " DESC");
		}
		
		return entityManager.createQuery(builder.toString(), User.class).getResultList();
	}

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
