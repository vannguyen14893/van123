package com.example.demo.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Group;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.ResourceNotFoundException;
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

	@SuppressWarnings("unchecked")
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
			builder.append(" AND LOWER(u.email) like '%" + filter.getFullName().toLowerCase()
					+ "%' or cast(u.status AS string) like '%" + filter.getFullName().toLowerCase() + "%' ");
		}
		if (!StringUtils.isNotBlank(filter.getSortName())) {
			builder.append(SORT + "u.userId");
		} else {
			builder.append(SORT + "u." + filter.getSortName() + "");
			builder.append(filter.getSort() ? " ASC" : " DESC");
		}
		Query query = entityManager.createQuery(builder.toString(), User.class);
		query.setFirstResult((filter.getPage() - 1) * filter.getPageSize());
		query.setMaxResults(filter.getPageSize());
		return query.getResultList();
	}

	@SuppressWarnings("unused")
	public Long count() {
		Query queryTotal = entityManager.createQuery("Select count(u.id) from User u");
		Long countResult = (Long) queryTotal.getSingleResult();
		UserFilterKeyword filter = new UserFilterKeyword();
		int pageNumber = (int) ((countResult / filter.getPageSize()) + 1);
		filter.setPageNumber(pageNumber);
		System.out.println(filter.getPageNumber());
		return countResult;
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
//		List<MultipartFile> files = new ArrayList<MultipartFile>();
//		StringBuilder builder = new StringBuilder();
//		try {
//			for (MultipartFile file : files) {
//				file = user.getFile();
//				builder.append(file.getOriginalFilename());
//				File fileNew = new File("D:" + builder);
//				FileOutputStream outputStream = new FileOutputStream(fileNew);
//				outputStream.write(file.getBytes());
//				outputStream.close();
//			}
//			user.setAvatar(builder.toString());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

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

	public void deleteUser(Integer userId) {
		User user = userRepository.getOne(userId);
		if (user != null) {
			user.setStatus(1);
			userRepository.save(user);
		}
		
	}
}
