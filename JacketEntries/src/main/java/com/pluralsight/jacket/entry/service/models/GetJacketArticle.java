package com.pluralsight.jacket.entry.service.models;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class GetJacketArticle {
	private Long userId;
	private String url;
	private String title;
	private Long entryId;
	private Image image;
	private List<String> tags;
	boolean isArchived;
	boolean isFavourite;

	public GetJacketArticle(Long userId, String url, String title, Long entryId) {
		this.setUserId(userId);
		this.url = url;
		this.title = title;
		tags = new ArrayList<String>();
		this.isArchived = false;
		this.isFavourite = false;
		this.setEntryId(entryId);
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
