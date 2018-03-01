package com.henri.devflow.core.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.henri.devflow.core.model.Role;
import com.henri.devflow.core.model.User;
import com.henri.devflow.core.model.repo.RoleRepository;
import com.henri.devflow.core.model.repo.UserRepository;
import com.henri.devflow.core.service.UserDetailsService;
import com.henri.devflow.util.ModelUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email)
	{
		User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(
              "No user found with username: "+ email);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return  new org.springframework.security.core.userdetails.User
          (user.getEmail(), 
          user.getPassword().toLowerCase(), enabled, accountNonExpired, 
          credentialsNonExpired, accountNonLocked, 
          getAuthorities(user.getRoles()));
	}

	private static Collection<GrantedAuthority> getAuthorities (Collection<Role> roles) {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getLabel()));
        }
        return authorities;
    }

	@Override
	public User get(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public User getByEmail(String email) {
		return userRepository.getByEmail(email);
	}

	@Override
	public Collection<User> getAll(Pageable pageable) {
		return userRepository.findAll();
	}
	
	@Override
	public User create(User model) {
		final Collection<Role> roles = roleRepository.findAll(ModelUtils.<Long, Role>getIds(model.getRoles()));
		model.setRoles(roles);
		
		model.setPassword(passwordEncoder.encode(model.getPassword()));
		return userRepository.save(model);
	}

	@Override
	public Collection<User> createAll(Collection<User> models) {
		return userRepository.save(models);
	}

	@Override
	public User update(User model) {
		return userRepository.save(model);
	}

	@Override
	public Collection<User> updateAll(Collection<User> models) {
		return userRepository.save(models);
	}

	@Override
	public boolean delete(Long id) {
		final User user = userRepository.getOne(id);
		if(user == null)
			return false;
		
		userRepository.delete(user);
		return true;
	}

	@Override
	public boolean deleteAll(Collection<Long> ids) {
		final Collection<User> users = userRepository.findAll(ids);
		if(users == null || users.isEmpty())
			return false;
		
		userRepository.delete(users);
		return true;
	}
	
	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public UserRepository repo() {
		return userRepository;
	}
}