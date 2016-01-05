package com.pluralsight.jacket.entry.data.models;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.pluralsight.jacket.data.models.BaseModel;

@Entity
@Table(name = "entries")
public class Entry extends BaseModel {

	private String url;
	private String title;
	private Blob image;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	
	
}
