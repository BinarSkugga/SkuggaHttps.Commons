package com.binarskugga.skuggahttps.validation;

import com.binarskugga.skuggahttps.validation.impl.*;

import java.util.*;
import java.util.function.*;

public class Validator {

	private Set<ValidationError> errors;

	public Validator() {
		errors = new HashSet<>();
	}

	public Set<ValidationError> done() {
		return this.errors;
	}

	public StringValidator string(String name, String value) {
		return new StringValidator(this, name.toUpperCase(), value);
	}

	public LongValidator integer(String name, Long value) {
		return new LongValidator(this, name.toUpperCase(), value);
	}

	public DoubleValidator floating(String name, Double value) {
		return new DoubleValidator(this, name.toUpperCase(), value);
	}

	public <T> ObjectValidator<T> object(String name, Class<T> type, T value) {
		return new ObjectValidator<>(this, name, type, value);
	}

	public void addError(ValidationError error) {
		this.errors.add(error);
	}

}
