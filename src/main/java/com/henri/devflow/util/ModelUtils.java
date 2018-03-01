package com.henri.devflow.util;

import java.util.ArrayList;
import java.util.Collection;

import com.henri.devflow.core.model.Model;

public class ModelUtils {
	public static <I,M extends Model<I>> Collection<I> getIds(Collection<M> models) {
		final Collection<I> ids = new ArrayList<>();
		for(Model<I> model : models) {
			ids.add(model.getId());
		}
		return ids;
	}
}
