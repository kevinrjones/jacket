package com.pluralsight.jacket.article.service;

import java.awt.Image;
import java.util.List;

import com.pluralsight.jacket.article.service.models.AddJacketArticle;
import com.pluralsight.jacket.article.service.models.GetJacketArticle;

/**
 * Created by kevin on 03/07/2015.
 */
public interface JacketArticleService {

    List<GetJacketArticle> getAllArticles(long id);
    GetJacketArticle getArticle(long id);
    long addArticle(AddJacketArticle entry);
    void updateArticle(AddJacketArticle entry);
	Image getArticleImage(Long entryId);
}
