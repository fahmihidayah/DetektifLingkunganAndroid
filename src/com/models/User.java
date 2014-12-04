package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.orm.SugarRecord;

import android.text.style.SuggestionSpan;
/*
"user": {
      "id": 1,
      "type": "PEMANTAU",
      "name": "fahmi",
      "email": "fahmi@rembugan.com",
      "status": "Alhamdulillah",
      "jumlahFollowerUser": 0,
      "jumlahFollowingUser": 0,
      "imageProfilePath": {
        "id": 1,
        "fileName": "6d249051_db1c_4704_8d78_d741ac02954a",
        "keterangan": "default profile",
        "urlImange": "http://192.168.1.5/uploads/6d249051_db1c_4704_8d78_d741ac02954a"
      },
      "isFollowing": false
    }
 */
public class User /*extends SugarRecord<User>*/ implements Serializable{
	public  Long idUser;
	public String type;
	public String name;
	public String email;
	public String status;
	public Integer jumlahFollowerUser = 0;
	public Integer jumlahFollowingUser = 0;
	public Boolean isFollowing;
	public ImagePath imageProfilePath;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}
	
	
	
}
