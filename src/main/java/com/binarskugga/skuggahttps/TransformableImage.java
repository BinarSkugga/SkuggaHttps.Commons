package com.binarskugga.skuggahttps;

import com.google.common.base.*;
import com.google.common.io.*;
import com.sun.net.httpserver.*;
import lombok.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

@NoArgsConstructor
public class TransformableImage implements HttpReturnable {

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

	public TransformableImage resize(int size) {
		return this.resize(size, size);
	}

	public TransformableImage resize(int width, int height) {
		ByteArrayInputStream in = new ByteArrayInputStream(this.getData());
		try {
			BufferedImage img = ImageIO.read(in);

			Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			ImageIO.write(imageBuff, this.getType().toLowerCase(), buffer);

			TransformableImage cropped = new TransformableImage();
			cropped.setData(buffer.toByteArray());
			cropped.setType(this.getType());
			return cropped;
		} catch (IOException e) {}
		return this;
	}

	@Override
	public void changeHeaders(Headers headers) {
		headers.set("Content-Type", "image/" + this.type.toLowerCase());
	}

	@Override
	public void write(OutputStream stream) throws IOException {
		stream.write(this.data);
	}

}
