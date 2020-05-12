package com.pluralsight.jacket;

import java.awt.Image;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface Capture {
	Image getImage(String url) throws ClientProtocolException, IOException;
}