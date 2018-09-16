package com.binarskugga.skuggahttps.validation;

import java.util.*;

public class Validator {

	private Set<ValidationError> errors;

	public Validator() {
		errors = new HashSet<>();
	}

	public Set<ValidationError> done() {
		return this.errors;
	}

	public ValueValidator field(String field) {
		return new ValueValidator(this, field.toUpperCase());
	}

	void addError(ValidationError error) {
		this.errors.add(error);
	}

}
