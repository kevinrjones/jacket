package com.pluralsight.jacket.entry.service;

import java.awt.Image;
import java.util.List;

import com.pluralsight.jacket.entry.service.models.AddJacketEntry;
import com.pluralsight.jacket.entry.service.models.GetJacketEntry;

/**
 * Created by kevin on 03/07/2015.
 */
public interface JacketEntryService {

    List<GetJacketEntry> getAllEntries(long id);
    GetJacketEntry getEntry(long id);
    long addEntry(AddJacketEntry entry);
    void updateEntry(AddJacketEntry entry);
	Image getEntryImage(Long entryId);
}
