package com.pluralsight.jacket.test.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.ProxySelector;

import javax.imageio.ImageIO;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.pluralsight.jacket.ScreenshotException;

public class ScreenshotLayerCapture {
	com.pluralsight.jacket.ScreenshotLayerCapture capture;

	@Before
	public void before() throws IOException, ScreenshotException {

		SystemDefaultRoutePlanner routePlanner = new SystemDefaultRoutePlanner(ProxySelector.getDefault());
		CloseableHttpClient client = HttpClients.custom().setRoutePlanner(routePlanner).build();

		capture = new com.pluralsight.jacket.ScreenshotLayerCapture(client);
	}

	@Test
	@Ignore
	public void should_retrieve_an_web_image_from_the_service() throws ClientProtocolException, IOException {
		String url = "http://stackoverflow.com/";

		Image img = capture.getImage(url);
		assertThat(img).isNotNull();
		File f = File.createTempFile("stackoverflow", ".png");

		System.err.println(f.getAbsolutePath());
		
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

		ImageIO.write(bimage, "png", f);
	}
}
