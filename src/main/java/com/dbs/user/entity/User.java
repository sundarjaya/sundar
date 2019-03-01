package com.dbs.user.entity;

import java.awt.Image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
 
@Entity
@Table(name = "user")
@ApiModel(description = "All details about the User. ")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
 
    @Column(name = "pwd")
    private String pwd;
    
    @Column(name = "first_name")
    private String firstName;
 
    @Column(name = "photo")
    private Image photo;
 
    @Column(name = "designation")
    private String designation;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
 
    public User() {
    }
 
    public User(String firstName, String designation,String email,String phone,Image photo) {
        super();
        this.firstName = firstName;
        this.designation = designation;
        this.email = email;
        this.phone = phone;
        this.photo=photo;
    }
 
    
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Image getPhoto() {
		return photo;
	}

	public void setPhoto(Image photo) {
		this.photo = photo;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
    public String toString() {
        return "User [userId=" + userId + ", firstName=" + firstName + ", email=" + email + ",phone=" + phone + ", designation="
                + designation + ",photo=" + photo + ",]";
    }
}