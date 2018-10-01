package com.binarskugga.skuggahttps.response;

import com.binarskugga.skuggahttps.*;
import lombok.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;

@NoArgsConstructor
public class HttpImage implements HttpReturnable {

	@Getter @Setter private String type;
	@Getter @Setter private byte[] data;

	public HttpImage(String type, String base64) {
		this.type = type;
		this.data = Base64.getDecoder().decode(base64.getBytes(Charset.forName("UTF-8")));
	}

	public HttpImage(File file) {
		try {
			String name = file.getName();
			this.type = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
			BufferedImage bimg = ImageIO.read(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bimg, this.type, bos);
			this.data = bos.toByteArray();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public HttpImage resize(int size) {
		return this.resize(size, size);
	}

	public HttpImage resize(int width, int height) {
		ByteArrayInputStream in = new ByteArrayInputStream(this.getData());
		try {
			BufferedImage img = ImageIO.read(in);

			Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			ImageIO.write(imageBuff, this.getType().toLowerCase(), buffer);

			HttpImage cropped = new HttpImage();
			cropped.setData(buffer.toByteArray());
			cropped.setType(this.getType());
			return cropped;
		} catch (IOException e) {}
		return this;
	}

	@Override
	public String contentType() {
		return "image/" + this.type.toLowerCase();
	}

	@Override
	public void write(OutputStream stream, HttpResponseHandler handler) throws IOException {
		stream.write(this.data);
	}

}
