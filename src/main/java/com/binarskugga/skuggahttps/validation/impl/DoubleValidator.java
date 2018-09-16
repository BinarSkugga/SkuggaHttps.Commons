package com.binarskugga.skuggahttps.validation.impl;

import com.binarskugga.skuggahttps.validation.*;

public class DoubleValidator extends ValueValidator<Double> {

	public DoubleValidator(Validator validator, String name, Double value) {
		super(validator, name, value);
	}

	public ValueValidator bounds(double min, double max) {
		if(this.value > max) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MAX.name()));
		if(this.value < min) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MIN.name()));
		return this;
	}

	public ValueValidator unsigned() {
		if(this.value < 0) this.validator.addError(new ValidationError(this.name, ValidationErrorType.UNSIGNED.name()));
		return this;
	}

}
