package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Menu;
import com.example.demo.service.impl.MenuServiceImpl;

@RestController
public class MenuController {
	@Autowired
	private MenuServiceImpl impl;

	@PostMapping(value = "/menu")
	public List<Menu> get(@RequestBody Menu menu) {
		return impl.findByParentId(menu.getParentId());
	}
	@GetMapping(value = "/get-menu/{roleId}")
	public List<Menu> get(@PathVariable("roleId") Integer roleId) {
		return impl.findByParentIdAndRoleId(roleId);
	}
	@PostMapping(value = "/get-menu")
	public Menu getMenuByIdAndParentId(@RequestBody Menu menu) {
		return impl.getMenuByIdAndParentId(menu.getId(), menu.getParentId());
	}

	@PostMapping(value = "/menu/add")
	public void add(@RequestBody Menu menu) {
		impl.addMenuRole(menu);
	}

	@PostMapping(value = "/menu/edit")
	public void edit(@RequestBody Menu menu) {
		impl.editMenu(menu);
	}

	@PostMapping(value = "/menu/check/add")
	public ResponseEntity<String> check(@RequestBody Menu menu) {
		if (impl.check(menu.getName())) {
			return new ResponseEntity<String>("okkkkkk", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("nooooooo", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/menu/check/edit")
	public ResponseEntity<String> checkNameEdit(@RequestBody Menu menu) {
		if (impl.checkNameEdit(menu.getId(), menu.getName())) {
			return new ResponseEntity<String>("okkkkkk", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("nooooooo", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/menu-child/add/{id}")
	public void addMenuChild(@PathVariable("id") Integer id, @RequestBody Menu menu) {
		impl.addMenuChild(id, menu);
	}

	@PostMapping(value = "/menu-child/update/{id}/parentId/{parentId}")
	public void updateMenuChild(@PathVariable("id") Integer id,@PathVariable("parentId") Integer parentId, @RequestBody Menu menu) {
		impl.editMenuChild(id, parentId, menu);
	}
}
