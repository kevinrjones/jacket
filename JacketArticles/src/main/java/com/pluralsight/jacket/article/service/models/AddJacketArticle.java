package com.pluralsight.jacket.article.service.models;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 30/06/2015.
 */
public class AddJacketArticle {
	private Long userId;
	private String url;
	private String title;
	private Long articleId;
	private BufferedImage image;
	private List<String> tags;
	boolean isArchived;
	boolean isFavourite;

	public AddJacketArticle(Long userId, String url, String title, Long entryId, BufferedImage image) {
		this.setUserId(userId);
		this.url = url;
		this.title = title;
		tags = new ArrayList<String>();
		this.isArchived = false;
		this.isFavourite = false;
		this.setImage(image);
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
		return "JacketArticle [url=" + url + ", title=" + title + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}

