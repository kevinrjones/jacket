package com.pluralsight.jacket;

import java.lang.String;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class Capture implements ICapture {

	HttpClient httpClient;
	
	public Capture(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	/* (non-Javadoc)
	 * @see com.pluralsight.jacket.ICapture#getImage(java.lang.String)
	 */
	@Override
	public Image getImage(String url) throws ClientProtocolException, IOException {
		
		if(url == null || url.isEmpty())
		{
			throw new IllegalArgumentException("url");
		}
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpget);
        HttpEntity entity = response.getEntity();
        
        if(response.getFirstHeader("Content-Type").getValue().startsWith("image")) {
            BufferedInputStream bis = new BufferedInputStream(entity.getContent());
        	
            return ImageIO.read(bis);        
        } else {
        	throw new CaptureException();
        }
        
	}
}
