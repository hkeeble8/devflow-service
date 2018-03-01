package com.henri.devflow.core.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henri.devflow.core.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{ }
