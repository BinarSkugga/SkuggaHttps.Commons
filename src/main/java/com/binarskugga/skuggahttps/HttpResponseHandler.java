package com.binarskugga.skuggahttps;

public interface HttpResponseHandler {

	String defaultContentType();
	<T> byte[] toBytes(Class<T> clazz, T response);
	<T> T fromBytes(Class<T> clazz, byte[] body);

}
