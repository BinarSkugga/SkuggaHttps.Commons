package com.binarskugga.skuggahttps.response;

import com.binarskugga.skuggahttps.*;
import lombok.*;

import java.io.*;

@AllArgsConstructor
public class HttpOctetStream implements HttpReturnable {

	@Getter @Setter private byte[] internal;

	@Override
	public String contentType() {
		return "application/octet-stream";
	}

	@Override
	public void write(OutputStream stream, HttpResponseHandler handler) throws IOException {
		stream.write(this.internal);
	}

}
