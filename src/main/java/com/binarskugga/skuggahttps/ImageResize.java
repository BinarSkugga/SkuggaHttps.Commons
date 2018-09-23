package com.binarskugga.skuggahttps;

import lombok.*;

public enum ImageResize {
	SMALL(64), MEDIUM(256), LARGE(512);

	@Getter
	private int size;

	ImageResize(int size) {
		this.size = size;
	}
}
