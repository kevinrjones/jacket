package com.pluralsight.jacket.data.models;


import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article extends BaseModel {

	private String url;
	private String title;
	
	@Lob
	private byte[] image;
	

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
	
	
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
		
}
