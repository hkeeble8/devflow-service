package com.henri.devflow.core.resource.bean;

import java.util.ArrayList;
import java.util.Collection;

import com.henri.devflow.core.model.Role;

public class RoleBean implements Bean<RoleBean, Role, Long> {

	private Long id;
	private String label;
	
	public RoleBean() {
		/* Intentionally blank */
	}
	
	public RoleBean(Role role) {
		setId(role.getId());
		setLabel(role.getLabel());
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public RoleBean setId(Long id) {
		this.id = id;
		return this;
	}
	
	public String getLabel() {
		return label;
	}

	public RoleBean setLabel(String label) {
		this.label = label;
		return this;
	}

	@Override
	public Role toModel() {
		return new Role()
				.setId(getId())
				.setLabel(getLabel());
	}

	public static Collection<Role> toModels(Collection<RoleBean> beans) {
		final Collection<Role> models = new ArrayList<>();
		for(RoleBean bean : beans)
			models.add(bean.toModel());
		return models;
	}


	public static Collection<RoleBean> toBeans(Collection<Role> models) {
		final Collection<RoleBean> beans = new ArrayList<>();
		for(Role model : models)
			beans.add(new RoleBean(model));
		return beans;
	}
}
