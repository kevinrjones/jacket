package com.pluralsight.jacket.test.integration;

import static org.assertj.core.api.Assertions.*;

import java.awt.Image;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

public class Capture {
	com.pluralsight.jacket.Capture capture;
	
	@Before
	public void before() {
		
		HttpClient client = HttpClients.createDefault();
		
		capture = new com.pluralsight.jacket.Capture(client); 
	}
	
	@Test
	public void should_get_the_google_home_page_logo() throws ClientProtocolException, IOException {
		Image img = capture.getImage("https://www.google.co.uk/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		assertThat(img).isNotNull();
	}
}
