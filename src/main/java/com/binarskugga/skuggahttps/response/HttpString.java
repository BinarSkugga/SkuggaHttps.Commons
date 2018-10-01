package com.binarskugga.skuggahttps.response;

import com.binarskugga.skuggahttps.*;
import lombok.*;

import java.io.*;
import java.nio.charset.*;

@AllArgsConstructor
public class HttpString implements HttpReturnable {

	@Getter @Setter private String internal;

	@Override
	public String contentType() {
		return "text/plain";
	}

	@Override
	public void write(OutputStream stream, HttpResponseHandler handler) throws IOException {
		stream.write(this.internal.getBytes(Charset.forName("UTF-8")));
	}
}
