package com.binarskugga.skuggahttps;

import java.nio.charset.*;
import java.util.Collection;

public interface HttpJsonHandler extends HttpResponseHandler {

	<T> String toJson(Class<T> clazz, T response);
	<T> T fromJson(Class<T> clazz, String body);

	<T> String toJson(Class<Collection> collectionClazz, Class<T> clazz, Collection<T> response);
	<T> Collection<T> fromJson(Class<Collection> collectionClazz, Class<T> clazz, String body);

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

	@Override
	default <T> byte[] toBytes(Class<Collection> collectionClazz, Class<T> clazz, Collection<T> response) {
		return this.toJson(collectionClazz, clazz, response).getBytes(Charset.forName("UTF-8"));
	}

	@Override
	default <T> Collection<T> fromBytes(Class<Collection> collectionClazz, Class<T> clazz, byte[] body) {
		return this.fromJson(collectionClazz, clazz, new String(body, Charset.forName("UTF-8")));
	}

}
