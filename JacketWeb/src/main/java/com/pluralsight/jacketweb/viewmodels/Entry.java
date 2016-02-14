package com.pluralsight.jacketweb.viewmodels;

import com.pluralsight.jacket.entry.service.models.GetJacketEntry;

public class Entry {
	private String title;
	private long entryId;
	
	public Entry(GetJacketEntry e) {
		title = e.getTitle();
		entryId = e.getEntryId();
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
