package com.pluralsight.jacket;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.junit.Before;
import org.junit.Test;

public class CaptureTest {

	Capture capture;
	HttpClient client;

	@Before
	public void before() throws IOException {
		client = mock(HttpClient.class);
		HttpResponse response = mock(HttpResponse.class);
		HttpEntity entity = mock(HttpEntity.class);
		capture = new Capture(client);
		Header header = mock(Header.class);

		BufferedImage img = createImage();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(img, "png", os);
		InputStream stream = new ByteArrayInputStream(os.toByteArray());

		when(header.getValue()).thenReturn("image/jpeg");
		when(response.getEntity()).thenReturn(entity);
		when(response.getFirstHeader("Content-Type")).thenReturn(header);
		when(client.execute(anyObject())).thenReturn(response);
		when(entity.getContent()).thenReturn(stream);
	}

	private BufferedImage createImage() {
		int w = 100;
		int h = 100;
		int pix[] = new int[w * h];
		int index = 0;
		for (int y = 0; y < h; y++) {
			int red = (y * 255) / (h - 1);
			for (int x = 0; x < w; x++) {
				int blue = (x * 255) / (w - 1);
				pix[index++] = (255 << 24) | (red << 16) | blue;
			}
		}
		Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(w, h, pix, 0, w));
		BufferedImage bufferedImage = getRenderedImage(image);
		return bufferedImage;
	}

	private BufferedImage getRenderedImage(Image in) {
		int w = in.getWidth(null);
		int h = in.getHeight(null);
		int type = BufferedImage.TYPE_INT_RGB;
		BufferedImage out = new BufferedImage(w, h, type);
		Graphics2D g2 = out.createGraphics();
		g2.drawImage(in, 0, 0, null);
		g2.dispose();
		return out;
	}

	@Test
	public void get_image_should_return_an_image() throws ClientProtocolException, IOException {
		Image imgOut = capture.getImage("url");
		assertThat(imgOut).isNotNull();

	}

	@Test
	public void get_image_should_return_the_requested_image() throws ClientProtocolException, IOException {
		Image imgOut = capture.getImage("url");
		BufferedImage bufferedImage = getRenderedImage(imgOut);
		int color = bufferedImage.getRGB(50, 50);

		assertThat(color).isEqualTo(-8388480);
	}

	@Test(expected = IllegalArgumentException.class)
	public void get_image_should_throw_an_exception_if_url_is_null() throws ClientProtocolException, IOException {
		capture.getImage(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void get_image_should_throw_an_exception_if_url_is_empty() throws ClientProtocolException, IOException {
		capture.getImage("");
	}
}
