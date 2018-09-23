package com.binarskugga.skuggahttps;

import com.google.common.base.*;
import com.google.common.io.*;
import lombok.*;

import javax.imageio.*;
import java.awt.image.*;
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
			BufferedImage bimg = ImageIO.read(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bimg, this.type, bos);
			this.data = bos.toByteArray();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
