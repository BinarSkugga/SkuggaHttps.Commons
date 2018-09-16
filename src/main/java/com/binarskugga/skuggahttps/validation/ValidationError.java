package com.binarskugga.skuggahttps.validation;

public class ValidationError {

	private String field;
	private String type;

	public ValidationError(String field, String type) {
		this.field = field;
		this.type = type;
	}
}
