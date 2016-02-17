package com.pluralsight.jacket.entry.service;

import java.awt.Image;
import java.util.List;

import com.pluralsight.jacket.entry.service.models.AddJacketArticle;
import com.pluralsight.jacket.entry.service.models.GetJacketArticle;

/**
 * Created by kevin on 03/07/2015.
 */
public interface JacketArticleService {

    List<GetJacketArticle> getAllEntries(long id);
    GetJacketArticle getEntry(long id);
    long addEntry(AddJacketArticle entry);
    void updateEntry(AddJacketArticle entry);
	Image getEntryImage(Long entryId);
}
