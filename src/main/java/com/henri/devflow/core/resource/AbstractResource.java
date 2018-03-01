package com.henri.devflow.core.resource;

import java.io.Serializable;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.henri.devflow.core.model.Model;
import com.henri.devflow.core.resource.bean.Bean;
import com.henri.devflow.core.service.ModelService;
import com.henri.devflow.util.JsonTools;

public abstract class AbstractResource<I extends Serializable, M extends Model<I>, B extends Bean<B, M, I>, R extends JpaRepository<M, I>, S extends ModelService<I, M, R>> {
	
	@Autowired
	private S service;
	
	@GetMapping("/")
	public String getAllEndpoint(HttpServletResponse response, Pageable pageable) {
		return getAll(response, pageable);
	}
	
	@GetMapping("/{id}")
	public String getEndpoint(HttpServletResponse response, @PathVariable("id") I id) {
		return get(response, id);
	}
	
	@PostMapping("/")
	public void createEndpoint(HttpServletResponse response, @Valid @RequestBody B bean) {
		create(response, bean);
	}
	
	@PostMapping("/{id}")
	public void updateEndpoint(HttpServletResponse response, @Valid @RequestBody B bean) {
		update(response, bean);
	}
	
	@DeleteMapping
	public void deleteEndpoint(HttpServletResponse response, I id) {
		delete(response, id);
	}
	
	public String getAll(HttpServletResponse response, Pageable pageable) {
		response.setStatus(HttpServletResponse.SC_OK);
		return JsonTools.toJson(toBeans(service().getAll(pageable)));
	}
	
	public String get(HttpServletResponse response, I id) {
		response.setStatus(HttpServletResponse.SC_OK);
		final M model = service().get(id);
		if(model != null) {
			return JsonTools.toJson(toBean(model));	
		}
		else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return JsonTools.EMPTY_OBJECT;
		}
	}
	
	public void create(HttpServletResponse response, B bean) {
		service().create(bean.toModel());
		response.setStatus(HttpServletResponse.SC_CREATED);
	}
	
	public void update(HttpServletResponse response, B bean) {
		service().update(bean.toModel());
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	public void delete(HttpServletResponse response, I id) {
		if(service().delete(id)) {
			response.setStatus(HttpServletResponse.SC_OK);	
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	protected abstract B toBean(M model);
	
	protected abstract Collection<B> toBeans(Collection<M> models);
	
	public S service() {
		return service;
	}
}
