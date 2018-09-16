package com.binarskugga.skuggahttps.validation;

import lombok.*;

import java.util.*;

public abstract class ForeignInput {

	@Getter private boolean validated = false;

	public final Set<ValidationError> validate() {
		this.validated = true;
		return this.doValidate();
	}

	protected abstract Set<ValidationError> doValidate();

}
