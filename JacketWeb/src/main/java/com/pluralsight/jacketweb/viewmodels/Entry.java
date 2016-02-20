package com.pluralsight.jacketweb.viewmodels;

import com.pluralsight.jacket.article.service.models.GetJacketArticle;

public class Entry {
	private String title;
	private long entryId;
	
	public Entry(GetJacketArticle e) {
		title = e.getTitle();
		entryId = e.getArticleId();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public long getEntryId() {
		return entryId;
	}

	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}
}
