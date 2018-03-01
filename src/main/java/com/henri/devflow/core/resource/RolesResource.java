package com.henri.devflow.core.resource;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henri.devflow.core.model.Role;
import com.henri.devflow.core.model.repo.RoleRepository;
import com.henri.devflow.core.resource.bean.RoleBean;
import com.henri.devflow.core.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RolesResource extends AbstractResource<Long, Role, RoleBean, RoleRepository, RoleService> {
	@Override
	protected RoleBean toBean(Role model) {
		return new RoleBean(model);
	}

	@Override
	protected Collection<RoleBean> toBeans(Collection<Role> models) {
		return RoleBean.toBeans(models); 
	}
}
