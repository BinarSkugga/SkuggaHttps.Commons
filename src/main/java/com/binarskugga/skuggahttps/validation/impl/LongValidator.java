package com.binarskugga.skuggahttps.validation.impl;

import com.binarskugga.skuggahttps.validation.*;

public class LongValidator extends ValueValidator<LongValidator, Long> {

	public LongValidator(Validator validator, String name, Long value) {
		super(validator, name, value);
	}

	public LongValidator bounds(long min, long max) {
		if(this.value > max) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MAX.name()));
		if(this.value < min) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MIN.name()));
		return this;
	}

	public LongValidator unsigned() {
		if(this.value < 0) this.validator.addError(new ValidationError(this.name, ValidationErrorType.UNSIGNED.name()));
		return this;
	}

}
