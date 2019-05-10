package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByRoles_RoleId(Integer roleId); 
	List<User> findByGroups_GroupId(Integer groupId);
}
