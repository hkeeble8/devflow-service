package com.henri.devflow.core.model;

public interface Model<I> {
	I getId();
	Model<I> setId(I id);
}
