package com.binarskugga.skuggahttps;

import java.util.Collection;

public interface HttpResponseHandler {

	String defaultContentType();
	<T> byte[] toBytes(Class<T> clazz, T response);
	<T> T fromBytes(Class<T> clazz, byte[] body);

	<T> byte[] toBytes(Class<Collection> collectionClazz, Class<T> clazz, Collection<T> response);
	<T> Collection<T> fromBytes(Class<Collection> collectionClazz, Class<T> clazz, byte[] body);

}
