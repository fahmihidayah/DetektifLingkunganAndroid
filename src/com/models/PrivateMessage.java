package com.models;

import java.io.Serializable;
import java.util.ArrayList;

import com.orm.SugarRecord;

import static com.models.Constantstas.STATUS_UNREAD;

public class PrivateMessage /*extends SugarRecord<PrivateMessage> */implements Serializable{
	public long idPrivateMessage;
	public User user;
	public int unreadMessage = 0;
	public ArrayList<ChatMessage> listChatMessage= new ArrayList<ChatMessage>();
	public boolean isOld;
	public PrivateMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public long getIdPrivateMessage() {
		return idPrivateMessage;
	}
	public void setIdPrivateMessage(long idPrivateMessage) {
		this.idPrivateMessage = idPrivateMessage;
	}
	public boolean isOld() {
		return isOld;
	}
	public void setOld(boolean isOld) {
		this.isOld = isOld;
	}

	public ArrayList<ChatMessage> getListChatMessage() {
		return listChatMessage;
	}
	public void setListChatMessage(ArrayList<ChatMessage> listChatMessage) {
		this.listChatMessage = listChatMessage;
	}
	
	public void countUnreadMessage(){
		unreadMessage = 0;
		for (ChatMessage chatMessage : listChatMessage) {
			if(chatMessage.getStatus().equalsIgnoreCase(STATUS_UNREAD)){
				unreadMessage++;
			}
		}
	}
	
	public int getUnreadMessage() {
		return unreadMessage;
	}
	public void setUnreadMessage(int unreadMessage) {
		this.unreadMessage = unreadMessage;
	}
	
	public boolean isNew() {
		return isOld;
	}
	public void setNew(boolean isNew) {
		this.isOld = isNew;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (idPrivateMessage ^ (idPrivateMessage >>> 32));
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
		PrivateMessage other = (PrivateMessage) obj;
		if (idPrivateMessage != other.idPrivateMessage)
			return false;
		return true;
	}
	
}
