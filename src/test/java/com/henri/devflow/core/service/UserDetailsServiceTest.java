package com.henri.devflow.core.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.henri.devflow.SpringBootTestBase;
import com.henri.devflow.core.model.Role;
import com.henri.devflow.core.model.User;

public class UserDetailsServiceTest extends SpringBootTestBase {
	@Autowired
	UserDetailsService userService;
	
	@Autowired
	RoleService roleService;
	
	@After
	public void tearDown() {
		userService.deleteAll();
		roleService.deleteAll();
	}
	
	@Test
	public void testCreate() {
		final Role role = roleService.create(new Role().setLabel("USER"));
		
		final User user = userService.create(new User()
				.setEmail("henrikeeble@googlemail.com")
				.setFirstName("Henri")
				.setLastName("Keeble")
				.setPassword("password1")
				.setRoles(new ArrayList<>(Arrays.asList(role))));
		
		assertNotNull(user.getId());
	}
}
