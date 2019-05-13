package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
	Permission findByRoles_RoleId(Integer id);
}
