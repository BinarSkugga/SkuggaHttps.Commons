package com.binarskugga.skuggahttps.validation.impl;

import com.binarskugga.skuggahttps.validation.*;

import java.util.regex.*;

public class StringValidator extends ValueValidator<String> {

	private static Pattern MAIL_VALIDATOR = Pattern.compile(".+@.+\\..+", Pattern.CASE_INSENSITIVE);

	public StringValidator(Validator validator, String name, String value) {
		super(validator, name, value);
	}

	public ValueValidator length(int min, int max) {
		if(isNull) return this;
		if(this.value.length() > max) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MAX_LENGTH.name()));
		if(this.value.length() < min) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MIN_LENGTH.name()));
		return this;
	}

	public ValueValidator minLength(int min) {
		if(isNull) return this;
		if(this.value.length() < min) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MIN_LENGTH.name()));
		return this;
	}

	public ValueValidator maxLength(int max) {
		if(isNull) return this;
		if(this.value.length() > max) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MAX_LENGTH.name()));
		return this;
	}

	public ValueValidator mail() {
		if(isNull) return this;
		return this.pattern(MAIL_VALIDATOR);
	}

	public ValueValidator pattern(Pattern pattern) {
		if(isNull) return this;
		if(!pattern.matcher(this.value).matches()) this.validator.addError(new ValidationError(this.name, ValidationErrorType.WRONG_FORMAT.name()));
		return this;
	}

}
