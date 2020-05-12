package com.pluralsight.jacket.data.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Users")
public class User extends BaseModel implements Serializable  {

    private String firstName;

    private String lastName;

    @Column(unique=true, nullable = false)
    private String email;

    private String password;

    @OneToMany(mappedBy="user")
    private Set<Article> articles;
    
    public User() {}

    public User(User user) {
        this.id = user.id;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.password = user.password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Article> getEntries() {
		return articles;
	}

	public void setEntries(Set<Article> articles) {
		this.articles = articles;
	}

	private static final long serialVersionUID = 2738859149330833739L;
}