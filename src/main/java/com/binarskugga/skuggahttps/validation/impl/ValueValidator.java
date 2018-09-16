package com.binarskugga.skuggahttps.validation.impl;

import com.binarskugga.skuggahttps.validation.*;

import java.util.function.*;

public class ValueValidator<T> {
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

	public ValueValidator notNull() {
		if(this.value == null) {
			this.validator.addError(new ValidationError(this.name, ValidationErrorType.NULL.name()));
			this.isNull = true;
		}
		return this;
	}

	public ValueValidator predicate(String type, Predicate<T> validator) {
		if(isNull) return this;
		if(!validator.test(this.value)) this.validator.addError(new ValidationError(this.name, type));
		return this;
	}

}
