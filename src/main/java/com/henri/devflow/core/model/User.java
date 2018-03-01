package com.henri.devflow.core.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Model<Long>
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;
	
	public User() {
		/* Intentionally Blank */
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public User setId(Long id) {
		this.id = id;
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public Collection<Role> getRoles() {
		return roles;
	}
	
	public User setRoles(Collection<Role> roles) {
		this.roles = roles;
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Role))
			return false;
	
		final User other = (User) obj;
		return other.getId() == getId();
	}

	@Override
	public String toString() {
		return getEmail();
	}
}
