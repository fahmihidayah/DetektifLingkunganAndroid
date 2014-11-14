package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/*
{
  "status": "200",
  "message": "success",
  "data": {
    "authKey": "42961a23-9e42-41a1-abe0-fda800bfce6e",
    "user": {
      "id": 2,
      "userName": "f",
      "password": "f",
      "type": "PEMANTAU",
      "name": "fahmi",       
       "email": "fahmi@rembugan.com",
      "status": "i'm a detective"
    }
  }
}
 */
public class User implements Serializable{
	private Long id;
	private String userName;
	private String password;
	private String type;
	private String name;
	private String email;
	private String status;
	private Integer jumlahFollowerUser = 0;
	private Integer jumlahFollowingUser = 0;
	private Boolean isFollowing;
	private ImagePath imageProfilePath;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getJumlahFollowerUser() {
		return jumlahFollowerUser;
	}
	public void setJumlahFollowerUser(Integer jumlahFollowerUser) {
		this.jumlahFollowerUser = jumlahFollowerUser;
	}
	public Integer getJumlahFollowingUser() {
		return jumlahFollowingUser;
	}
	public void setJumlahFollowingUser(Integer jumlahFollowingUser) {
		this.jumlahFollowingUser = jumlahFollowingUser;
	}
	public Boolean getIsFollowing() {
		return isFollowing;
	}
	public void setIsFollowing(Boolean isFollowing) {
		this.isFollowing = isFollowing;
	}
	public ImagePath getImageProfilePath() {
		return imageProfilePath;
	}
	public void setImageProfilePath(ImagePath imageProfilePath) {
		this.imageProfilePath = imageProfilePath;
	}
	
	
}
