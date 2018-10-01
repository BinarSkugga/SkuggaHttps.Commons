package com.binarskugga.skuggahttps;

import java.nio.charset.*;

public interface HttpJsonHandler extends HttpResponseHandler {

	<T> String toJson(Class<T> clazz, T response);
	<T> T fromJson(Class<T> clazz, String body);

	@Override
	default String defaultContentType() {
		return "application/json";
	}

	@Override
	default <T> byte[] toBytes(Class<T> clazz, T response) {
		return this.toJson(clazz, response).getBytes(Charset.forName("UTF-8"));
	}

	@Override
	default <T> T fromBytes(Class<T> clazz, byte[] body) {
		return this.fromJson(clazz, new String(body, Charset.forName("UTF-8")));
	}

}
