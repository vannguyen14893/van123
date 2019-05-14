package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RoleRepository;

@Service
@Transactional
public class MenuServiceImpl {
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private RoleRepository roleRepository;

	public List<Menu> findByParentId(Integer parentId) {
		return menuRepository.findByParentId(parentId);
	}

	public void addMenuRole(Menu menu) {
		List<Role> roles = new ArrayList<Role>();
		for (int i = 0; i < menu.getRoleId().length; i++) {
			roles.add(roleRepository.getOne(menu.getRoleId()[i]));
		}
		menu.setRoles(roles);
		menuRepository.save(menu);
	}

	public Boolean check(String name) {
		Menu menu2 = menuRepository.findByName(name);
		if (menu2 != null) {
			return false;
		}
		return true;
	}

	public Boolean checkNameEdit(Integer id, String nameOld) {
		Menu menuOld = menuRepository.getOne(id);
		if (menuOld != null) {
			if (!menuOld.getName().equals(nameOld)) {
				Menu menu = menuRepository.findByName(nameOld);
				if (menu != null) {
					return false;
				} else {
					return true;
				}
			}
		}

		return true;
	}

	public void editMenu(Menu menu) {
		Menu menu2 = menuRepository.getOne(menu.getId());
		if (menu2 != null) {
			menu2.setId(menu.getId());
			menu2.setName(menu.getName());
			menu2.setParentId(menu.getParentId());
			menu2.setLink(menu.getLink());
			menu2.getRoles().clear();
			List<Role> roles = menu.getRoles();
			for (int i = 0; i < menu.getRoleId().length; i++) {
				roles.add(roleRepository.getOne(menu.getRoleId()[i]));
			}
			menu2.setRoles(roles);
		}
		menuRepository.save(menu2);
	}
}
