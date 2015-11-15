package com.pluralsight.jacket.security.data.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pluralsight.jacket.data.models.BaseModel;

@Entity
@Table(name = "Users")
public class User extends BaseModel implements Serializable  {

//    @NotEmpty(message = "First name is required.")
    private String firstName;

//    @NotEmpty(message = "Last name is required.")
    private String lastName;

//    @Email(message = "Please provide a valid email address.")
//    @NotEmpty(message = "Email is required.")
    @Column(unique=true, nullable = false)
    private String email;

//    @NotEmpty(message = "Password is required.")
    private String password;

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

    private static final long serialVersionUID = 2738859149330833739L;
}