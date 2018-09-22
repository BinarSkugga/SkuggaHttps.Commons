package com.binarskugga.skuggahttps.validation.impl;

import com.binarskugga.skuggahttps.validation.*;

import java.util.function.*;

public abstract class ValueValidator<V extends ValueValidator, T> {
	protected Validator validator;
	protected String name;

	protected transient T value;
	protected transient boolean isNull = false;

	public ValueValidator(Validator validator, String name, T value) {
		this.value = value;
		this.validator = validator;
		this.name = name;
	}

	public Validator next() {
		return this.validator;
	}

	public abstract V notNull();
	public abstract V predicate(String type, Predicate<T> condition);

}
