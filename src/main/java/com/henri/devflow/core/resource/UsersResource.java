package com.henri.devflow.core.resource;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henri.devflow.core.model.User;
import com.henri.devflow.core.model.repo.UserRepository;
import com.henri.devflow.core.resource.bean.UserBean;
import com.henri.devflow.core.service.UserDetailsService;

@RestController
@RequestMapping("/api/users")
public class UsersResource extends AbstractResource<Long, User, UserBean, UserRepository, UserDetailsService> {
	@Override
	protected UserBean toBean(User model) {
		return new UserBean(model);
	}

	@Override
	protected Collection<UserBean> toBeans(Collection<User> models) {
		return UserBean.toBeans(models);
	}
}
