package com.pluralsight.jacket.test.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Image;
import java.io.IOException;
import java.net.ProxySelector;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class Capture {
	com.pluralsight.jacket.Capture capture;
	
	@Before
	public void before() {
		
		SystemDefaultRoutePlanner routePlanner = new SystemDefaultRoutePlanner(
		        ProxySelector.getDefault());
		CloseableHttpClient client = HttpClients.custom()
		        .setRoutePlanner(routePlanner)
		        .build();
		
//		HttpClient client = HttpClients.createDefault();
		
		capture = new com.pluralsight.jacket.Capture(client); 
	}
	
	@Test
	public void should_get_the_google_home_page_logo() throws ClientProtocolException, IOException {
		Image img = capture.getImage("https://www.google.co.uk/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		assertThat(img).isNotNull();
	}
	
	@Test
	public void should_retrieve_an_web_image_from_the_service() throws ClientProtocolException, IOException {
		String access_key = "c53dbd893386fecfed94ec888367fc03";
		String snapshotUrl = "http://www.bbc.co.uk/sport/beta";
		String url = "http://api.screenshotlayer.com/api/capture?access_key=" + access_key + "&url=" + snapshotUrl + "&width=350";
		
		//http://api.screenshotlayer.com/api/capture?access_key=c53dbd893386fecfed94ec888367fc03&url=http://apple.com&viewport=1440x900&fullpage=1
		Image img = capture.getImage(url);                
		assertThat(img).isNotNull();
	}
}
