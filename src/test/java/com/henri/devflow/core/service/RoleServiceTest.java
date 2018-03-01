package com.henri.devflow.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.henri.devflow.SpringBootTestBase;
import com.henri.devflow.core.model.Role;
import com.henri.devflow.util.ModelUtils;

public class RoleServiceTest extends SpringBootTestBase {
	
	@Autowired
	private RoleService roleService;
	
	@Test
	public void testCreate() {		
		final Role role = roleService.create(role());	
		assertNotNull(role.getId());
		roleService.delete(role.getId());
	}
	
	@Test
	public void testCreateAll() {
		final ArrayList<Role> roles = new ArrayList<>(roleService.createAll(Arrays.asList(role(), role())));
		try {
			roleService.getAll(new PageRequest(1, 2));
			assertNotNull(roles.get(0).getId());
			assertNotNull(roles.get(1).getId());
		} finally {
			roleService.deleteAll(ModelUtils.getIds(roles));
		}
	}
	
	@Test
	public void testDelete() {
		final Role role = roleService.create(role());
		roleService.delete(role.getId());
		assertNull(roleService.get(role.getId()));
	}
	
	@Test
	public void testDeleteSet() {
		final ArrayList<Role> roles = new ArrayList<>(roleService.createAll(Arrays.asList(role(), role())));
		roleService.deleteAll(ModelUtils.getIds(roles));
		assertTrue(roleService.getAll(new PageRequest(1, 10)).size() == 1);
	}
	
	@Test
	public void testUpdate() {
		final Role role = roleService.create(role());
		
		try {
			role.setLabel("NEW_ROLE");
			roleService.update(role);
			assertEquals("NEW_ROLE", roleService.get(role.getId()).getLabel());
		} finally {
			roleService.delete(role.getId());	
		}
	}
	
	@Test
	public void testUpdateAll() {
		final ArrayList<Role> roles = new ArrayList<>(roleService.createAll(Arrays.asList(role(), role())));
		
		try {
			for(Role role : roles)
				role.setLabel("NEW_ROLE");
			final ArrayList<Role> newRoles = new ArrayList<>(roleService.updateAll(roles));
			assertEquals("NEW_ROLE", newRoles.get(0).getLabel());
			assertEquals("NEW_ROLE", newRoles.get(1).getLabel());
		} finally {
			roleService.deleteAll(ModelUtils.getIds(roles));
		}
	}
	
	private Role role() {
		return new Role().setLabel("USER");
	}
}
