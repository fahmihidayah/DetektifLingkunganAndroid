package com.models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.orm.SugarRecord;

/*
 
 {
  "message": {
    "id": null,
    "userSender": {
      "id": 2,
      "type": "PEMANTAU",
      "name": "doni",
      "email": "doni@gmail.com",
      "status": "i'm a detective",
      "jumlahFollowerUser": 0,
      "jumlahFollowingUser": 0,
      "imageProfilePath": {
        "id": 1,
        "fileName": "60b39198_2e42_4d05_908c_158349362c64",
        "keterangan": "default profile",
        "urlImange": "http://192.168.1.12/uploads/60b39198_2e42_4d05_908c_158349362c64"
      },
      "isFollowing": false
    },
    "userReceiver": {
      "id": 1,
      "type": "PEMANTAU",
      "name": "fahmi",
      "email": "fahmi@rembugan.com",
      "status": "i'm a detective maaaaan",
      "jumlahFollowerUser": 0,
      "jumlahFollowingUser": 0,
      "imageProfilePath": {
        "id": 4,
        "fileName": "b0b7322f_5c8b_47ba_86b0_9dfe96862895",
        "keterangan": "modifed profile",
        "urlImange": "http://192.168.1.12/uploads/b0b7322f_5c8b_47ba_86b0_9dfe96862895"
      },
      "isFollowing": false
    },
    "messageData": "bro ini pesannya sangat panjang jadi hati-hati yaaaaaa!!! soalnya nanti bisa kelebaran...",
    "time": 1416960943340,
    "status": "UNREAD"
  }
}
 */
public class ChatMessage /*extends SugarRecord<ChatMessage>*/ implements Serializable {
	public Long idpublicMessage;
	public User userSender;
	public User userReceiver;
	public String messageData;
//	public Date time;
	public String status;
	public boolean isSendMessage;
	
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdpublicMessage() {
		return idpublicMessage;
	}
	public void setIdpublicMessage(Long idpublicMessage) {
		this.idpublicMessage = idpublicMessage;
	}
	public void setSendMessage(boolean isSendMessage) {
		this.isSendMessage = isSendMessage;
	}
	public User getUserSender() {
		return userSender;
	}
	public void setUserSender(User userSender) {
		this.userSender = userSender;
	}
	public User getUserReceiver() {
		return userReceiver;
	}
	public void setUserReceiver(User userReceiver) {
		this.userReceiver = userReceiver;
	}
	public String getMessageData() {
		return messageData;
	}
	public void setMessageData(String messageData) {
		this.messageData = messageData;
	}
//	public Calendar getTime() {
//		return time;
//	}
//	public void setTime(Calendar time) {
//		this.time = time;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean getIsSendMessage() {
		return isSendMessage;
	}
	public void setIsSendMessage(boolean isSendMessage) {
		this.isSendMessage = isSendMessage;
	}
	
}
