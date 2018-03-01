package com.henri.devflow.core.service.impl;

import org.springframework.stereotype.Service;

import com.henri.devflow.core.model.Role;
import com.henri.devflow.core.model.repo.RoleRepository;
import com.henri.devflow.core.service.AbstractModelService;
import com.henri.devflow.core.service.RoleService;

@Service
public class RoleServiceImpl extends AbstractModelService<Long, Role, RoleRepository> implements RoleService {

}
