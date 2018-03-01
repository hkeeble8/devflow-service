package com.henri.devflow.core.service;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.henri.devflow.core.model.Model;

public abstract class AbstractModelService<I extends Serializable, M extends Model<I>, R extends JpaRepository<M, I>> implements ModelService<I,M,R> {
	
	@Autowired
	private R repo;
	
	@Override
	public M get(I id) {
		return repo.findOne(id);
	}
	
	@Override
	public Collection<M> getAll(Pageable pageable) {
		return (Collection<M>) repo.findAll();
	}
	
	@Override
	public M create(M model) {
		return repo().save(model);
	}
	
	@Override
	public Collection<M> createAll(Collection<M> models) {
		return repo().save(models);
	}
	
	@Override
	public M update(M model) {
		return repo().save(model);
	}
	
	@Override
	public Collection<M> updateAll(Collection<M> models) {
		return repo().save(models);
	}
	
	@Override
	public boolean delete(I id) {
		final M model = (M) repo.getOne(id);
		if(model == null)
			return false;
		
		repo().delete(id);
		return true;
	}
	
	@Override
	public boolean deleteAll(Collection<I> ids) {
		final Collection<M> models = (Collection<M>) repo.findAll(ids);
		if(models == null || models.isEmpty())
			return false;
		
		repo().delete(models);
		return true;	
	}

	@Override
	public void deleteAll() {
		repo().deleteAll();
	}
	
	@Override
	public R repo() {
		return repo;
	}
}
