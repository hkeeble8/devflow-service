package com.henri.devflow.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Model<Long>
{
	@SuppressWarnings("unused")
	private static final Role SUPER_USER = new Role().setId(1L).setLabel("SUPER_USER");
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "label")
	private String label;

	public Role() {
		/* Intentionally blank */
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Role setId(Long id) {
		this.id = id;
		return this;
	}

	public String getLabel() {
		return label;
	}

	public Role setLabel(String label) {
		this.label = label;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Role))
			return false;
	
		final Role other = (Role) obj;
		return other.getId() == getId();
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
