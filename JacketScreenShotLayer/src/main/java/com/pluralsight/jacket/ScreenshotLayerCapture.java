package com.pluralsight.jacket;

import java.lang.String;
import java.util.Properties;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class ScreenshotLayerCapture implements Capture {

	HttpClient httpClient;
	Properties properties;

	String access_key;
	String baseUrl = "http://api.screenshotlayer.com/api/capture";

	public ScreenshotLayerCapture(HttpClient httpClient) throws IOException, ScreenshotException {
		this.httpClient = httpClient;
		properties = new Properties();

		InputStream is = getClass().getClassLoader().getResourceAsStream("local.properties");

		if (is != null) {
			properties.load(is);

			access_key = properties.getProperty("key"); // load from local
		} else {
			throw new ScreenshotException("Unable to load properties to read the ScreenshotLayer access key");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pluralsight.jacket.ICapture#getImage(java.lang.String)
	 */
	@Override
	public Image getImage(String url) throws ClientProtocolException, IOException {

		if (url == null || url.isEmpty()) {
			throw new IllegalArgumentException("url");
		}

		String captureUrl = baseUrl + "?access_key=" + access_key + "&url=" + url + "&width=270";

		System.err.println(captureUrl);

		HttpGet httpget = new HttpGet(captureUrl);
		HttpResponse response = httpClient.execute(httpget);
		HttpEntity entity = response.getEntity();

		if (response.getFirstHeader("Content-Type").getValue().startsWith("image")) {
			BufferedInputStream bis = new BufferedInputStream(entity.getContent());
			return ImageIO.read(bis);
		} else {
			throw new CaptureException();
		}

	}
}
