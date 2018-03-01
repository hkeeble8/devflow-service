package com.henri.devflow.core.resource.bean;

import java.util.ArrayList;
import java.util.Collection;

import com.henri.devflow.core.model.User;

public class UserBean implements Bean<UserBean, User, Long> {

	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private Collection<RoleBean> roles;
	
	public UserBean() {
		/* Intentionally blank */
	}
	
	public UserBean(User user) {
		setId(user.getId());
		setEmail(user.getEmail());
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setRoles(RoleBean.toBeans(user.getRoles()));
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public UserBean setId(Long id) {
		this.id = id;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public UserBean setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public UserBean setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public UserBean setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Collection<RoleBean> getRoles() {
		return roles;
	}

	public UserBean setRoles(Collection<RoleBean> roles) {
		this.roles = roles;
		return this;
	}
	
	public String getPassword() {
		return password;
	}

	public UserBean setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public User toModel() {
		return new User()
				.setId(getId())
				.setEmail(getEmail())
				.setFirstName(getFirstName())
				.setLastName(getLastName())
				.setPassword(getPassword())
				.setRoles(RoleBean.toModels(getRoles()));
	}

	public static Collection<User> toModels(Collection<UserBean> beans) {
		final Collection<User> models = new ArrayList<>();
		for(UserBean bean : beans)
			models.add(bean.toModel());
		return models;
	}

	public static Collection<UserBean> toBeans(Collection<User> models) {
		final Collection<UserBean> beans = new ArrayList<>();
		for(User model : models)
			beans.add(new UserBean(model));
		return beans;
	}
}
