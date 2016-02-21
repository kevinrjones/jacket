package com.pluralsight.jacketweb.viewmodels;

import com.pluralsight.jacket.article.service.models.GetJacketArticle;

public class Article {
	private String title;
	private long articleId;
	
	public Article(GetJacketArticle e) {
		title = e.getTitle();
		articleId = e.getArticleId();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
}
