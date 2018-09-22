package com.binarskugga.skuggahttps.validation.impl;

import com.binarskugga.skuggahttps.validation.*;

import java.util.function.*;

public class ObjectValidator<T> extends ValueValidator<ObjectValidator, T> {

	private Class objectType;

	public ObjectValidator(Validator validator, String name, Class<T> objectType, T value) {
		super(validator, name, value);
		this.objectType = objectType;
	}

	@Override
	public ObjectValidator<T> notNull() {
		if(this.value == null) this.validator.addError(new ValidationError(this.name, ValidationErrorType.NULL.name()));
		return this;
	}

	@Override
	public ObjectValidator<T> predicate(String type, Predicate<T> condition) {
		if(!condition.test(this.value)) this.validator.addError(new ValidationError(this.name, type.toUpperCase()));
		return this;
	}

}
