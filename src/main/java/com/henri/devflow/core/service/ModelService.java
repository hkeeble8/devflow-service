package com.henri.devflow.core.service;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.henri.devflow.core.model.Model;

public interface ModelService <I extends Serializable, M extends Model<I>, R extends JpaRepository<M,I>> {
	M get(I id);	
	Collection<M> getAll(Pageable pageable);
	M create(M model);
	Collection<M> createAll(Collection<M> models);
	M update(M model);
	Collection<M> updateAll(Collection<M> models);
	boolean delete(I id);
	boolean deleteAll(Collection<I> ids);
	void deleteAll();
	R repo();
}
