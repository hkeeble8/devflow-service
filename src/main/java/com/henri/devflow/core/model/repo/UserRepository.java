package com.henri.devflow.core.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henri.devflow.core.model.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	User getByEmail(String email);
}
