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

	public FieldValidator field(String field) {
		return new FieldValidator(this, field.toUpperCase());
	}

	void addError(ValidationError error) {
		this.errors.add(error);
	}

}
