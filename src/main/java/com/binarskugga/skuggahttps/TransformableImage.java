package com.binarskugga.skuggahttps;

import com.google.common.base.*;
import com.google.common.io.*;
import lombok.*;

import java.io.*;
import java.util.*;

@NoArgsConstructor
public class TransformableImage {

	@Getter @Setter private String type;
	@Getter @Setter private byte[] data;

	public TransformableImage(String type, String base64) {
		this.type = type;
		this.data = Base64.getDecoder().decode(base64.getBytes(Charsets.UTF_8));
	}

	public TransformableImage(File file) {
		try {
			this.type = Files.getFileExtension(file.getName()).toUpperCase();
			this.data = Files.asCharSource(file, Charsets.UTF_8).read().getBytes(Charsets.UTF_8);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
