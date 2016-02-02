package com.pluralsight.jacket.entry.service.models;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.pluralsight.jacket.data.models.BaseModel;

/**
 * Created by kevin on 30/06/2015.
 */
public class JacketEntry {
	private long userId;
	String url;
    String title;
    private Image image;
    List<String> tags;
    boolean isArchived;
    boolean isFavourite;

    public JacketEntry(long userId, String url, String title, Image image) {
    	this.setUserId(userId);
        this.url = url;
        this.title = title;
        tags = new ArrayList<String>();
        this.isArchived = false;
        this.isFavourite = false;
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void ToggleFavoutite() {
        isFavourite = !isFavourite;
    }

    public void ToggleArchive() {
        isArchived = !isArchived;
    }

    public void addTag(String tag) {
        // todo: tag should not exist in list already
        tags.add(tag);
    }

    public void removeTag(String tag) {
        // todo: tag should exist in list already
        tags.remove(tag);
    }

    @Override
	public String toString() {
		return "JacketEntry [url=" + url + ", title=" + title + "]";
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
