package com.models;

import java.io.Serializable;

/*
 * {
status: "200"
message: "success"
data: [1]
0:  {
id: 2
dataKomentar: "nah loooh"
user: {
id: 1
userName: "f"
password: "f"
type: "PEMANTAU"
name: "fahmi"
email: "fahmi@rembugan.com"
status: "i'm a detective"
jumlahFollowerUser: 0
jumlahFollowingUser: 0
}-
}-
-
}
 */
public class Komentar implements Serializable {
	private Long id;
	private String dataKomentar;
	private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDataKomentar() {
		return dataKomentar;
	}
	public void setDataKomentar(String dataKomentar) {
		this.dataKomentar = dataKomentar;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
