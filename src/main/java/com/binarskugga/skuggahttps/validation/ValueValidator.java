package com.binarskugga.skuggahttps.validation;

import java.util.function.*;
import java.util.regex.*;

public class ValueValidator {

	private static Pattern MAIL_VALIDATOR = Pattern.compile(".+@.+\\..+", Pattern.CASE_INSENSITIVE);

	private Validator validator;
	private String name;
	private transient boolean isNull = false;

	public ValueValidator(Validator validator, String name) {
		this.validator = validator;
		this.name = name;
	}

	public Validator next() {
		return this.validator;
	}

	public ValueValidator length(int min, int max, String value) {
		if(isNull) return this;
		if(value.length() > max) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MAX_LENGTH.name()));
		if(value.length() < min) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MIN_LENGTH.name()));
		return this;
	}

	public ValueValidator minLength(int min, String value) {
		if(isNull) return this;
		if(value.length() < min) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MIN_LENGTH.name()));
		return this;
	}

	public ValueValidator maxLength(int max, String value) {
		if(isNull) return this;
		if(value.length() > max) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MAX_LENGTH.name()));
		return this;
	}

	public ValueValidator bounds(long min, long max, long value) {
		if(value > max) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MAX.name()));
		if(value < min) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MIN.name()));
		return this;
	}

	public ValueValidator bounds(double min, double max, double value) {
		if(value > max) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MAX.name()));
		if(value < min) this.validator.addError(new ValidationError(this.name, ValidationErrorType.MIN.name()));
		return this;
	}

	public ValueValidator notNull(Object value) {
		if(value == null) {
			this.validator.addError(new ValidationError(this.name, ValidationErrorType.NULL.name()));
			this.isNull = true;
		}
		return this;
	}

	public ValueValidator mail(String value) {
		if(isNull) return this;
		return this.pattern(MAIL_VALIDATOR, value);
	}

	public ValueValidator pattern(Pattern pattern, String value) {
		if(isNull) return this;
		if(!pattern.matcher(value).matches()) this.validator.addError(new ValidationError(this.name, ValidationErrorType.WRONG_FORMAT.name()));
		return this;
	}

	public <T> ValueValidator custom(T object, String type, Predicate<T> validator) {
		if(isNull) return this;
		if(!validator.test(object)) this.validator.addError(new ValidationError(this.name, type));
		return this;
	}

}
