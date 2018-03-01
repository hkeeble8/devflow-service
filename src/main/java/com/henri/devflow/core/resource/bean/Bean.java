package com.henri.devflow.core.resource.bean;

import com.henri.devflow.core.model.Model;

public interface Bean<B extends Bean<B, M, I>, M extends Model<I>, I> {
	I getId();
	B setId(I id);
	M toModel();
}
