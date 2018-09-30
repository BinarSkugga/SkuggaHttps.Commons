package com.binarskugga.skuggahttps;

import java.io.*;

public interface HttpReturnable {

	String contentType();
	void write(OutputStream stream, HttpResponseHandler handler) throws IOException;

}
