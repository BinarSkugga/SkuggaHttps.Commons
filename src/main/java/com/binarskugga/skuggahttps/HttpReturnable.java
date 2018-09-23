package com.binarskugga.skuggahttps;

import com.sun.net.httpserver.*;

import java.io.*;

public interface HttpReturnable {

	void changeHeaders(Headers headers);
	void write(OutputStream stream) throws IOException;

}
