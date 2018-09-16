package com.binarskugga.skuggahttps.validation;

import java.util.function.*;
import java.util.regex.*;

public class FieldValidator {

	private static Pattern MAIL_VALIDATOR = Pattern.compile("/.+@.+\\..+", Pattern.CASE_INSENSITIVE);

	private Validator validator;
	private String field;

	public FieldValidator(Validator validator, String field) {
		this.validator = validator;
		this.field = field;
	}

	public Validator done() {
		return this.validator;
	}

	public FieldValidator length(int min, int max, String value) {
		if(value.length() > max) this.validator.addError(new ValidationError(this.field, ValidationErrorType.MAX_LENGTH.name()));
		if(value.length() < min) this.validator.addError(new ValidationError(this.field, ValidationErrorType.MIN_LENGTH.name()));
		return this;
	}

	public FieldValidator bounds(long min, long max, long value) {
		if(value > max) this.validator.addError(new ValidationError(this.field, ValidationErrorType.MAX.name()));
		if(value < min) this.validator.addError(new ValidationError(this.field, ValidationErrorType.MIN.name()));
		return this;
	}

	public FieldValidator bounds(double min, double max, double value) {
		if(value > max) this.validator.addError(new ValidationError(this.field, ValidationErrorType.MAX.name()));
		if(value < min) this.validator.addError(new ValidationError(this.field, ValidationErrorType.MIN.name()));
		return this;
	}

	public FieldValidator mail(String value) {
		return this.pattern(MAIL_VALIDATOR, value);
	}

	public FieldValidator pattern(Pattern pattern, String value) {
		if(!pattern.matcher(value).matches()) this.validator.addError(new ValidationError(this.field, ValidationErrorType.WRONG_FORMAT.name()));
		return this;
	}

	public <T> FieldValidator custom(T object, String type, Predicate<T> validator) {
		if(!validator.test(object)) this.validator.addError(new ValidationError(this.field, type));
		return this;
	}

}
