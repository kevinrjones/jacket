package com.pluralsight.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;

public class ImageConverter {
	public static Image getImageFromByteArray(byte[] imageBytes, Log log) {
		Image image = null;

		try {
			InputStream in = new ByteArrayInputStream(imageBytes);
			image = ImageIO.read(in);
		} catch (IOException e) {
			log.error("Unable to read Blob", e);
			e.printStackTrace();
		}
		return image;
	}

	public static Image getImageFromStream(InputStream inputStream, Log log) {
		Image image = null;

		try {
			image = ImageIO.read(inputStream);
		} catch (IOException e) {
			log.error("Unable to read Blob", e);
			e.printStackTrace();
		}
		return image;
	}

	public static byte[] getByteArrayFromImage(BufferedImage image, Log log) {

		if (image == null)
			throw new IllegalArgumentException();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] imageData = null;

		try {
			ImageIO.write(image, "png", baos);
			imageData = baos.toByteArray();
		} catch (IOException e) {
			log.error("Unable to get bytes from image");
			e.printStackTrace();
		}
		return imageData;
	}

}
