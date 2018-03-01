package com.henri.devflow.core.service;

import com.henri.devflow.core.model.User;
import com.henri.devflow.core.model.repo.UserRepository;

public interface UserDetailsService  extends org.springframework.security.core.userdetails.UserDetailsService, ModelService<Long, User, UserRepository> {
	User getByEmail(String email);
}
