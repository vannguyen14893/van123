package com.example.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	@PersistenceContext
	EntityManager entityManager;

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

	public List<Role> getAll() {
		int pageNumber = 1;
		int pageSize = 10;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(Role.class)));
		Long count = entityManager.createQuery(countQuery).getSingleResult();
		System.out.println(count);
		CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
		Root<Role> from = criteriaQuery.from(Role.class);
		CriteriaQuery<Role> select = criteriaQuery.select(from);
		TypedQuery<Role> typedQuery = entityManager.createQuery(select);
		while (pageNumber < count.intValue()) {
			typedQuery.setFirstResult(pageNumber - 1);
			typedQuery.setMaxResults(pageSize);
			System.out.println("Current page: " + typedQuery.getResultList());
			pageNumber += pageSize;
		}
		return typedQuery.getResultList();
	}
}
