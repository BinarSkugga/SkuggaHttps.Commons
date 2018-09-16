package com.binarskugga.skuggahttps.validation;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
public class ValidationError {

	@Getter @Setter private String field;
	@Getter @Setter private String type;

}
