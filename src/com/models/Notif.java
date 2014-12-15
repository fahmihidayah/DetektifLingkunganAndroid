package com.models;

import java.util.Calendar;

public class Notif {
	private Long idNotif;
	private String notifInfo;
	private Laporan laporan;
//	private Calendar time;
	private String typeNotif;
	private User user;
	public Long getIdNotif() {
		return idNotif;
	}
	public void setIdNotif(Long idNotif) {
		this.idNotif = idNotif;
	}
	public String getNotifInfo() {
		return notifInfo;
	}
	public void setNotifInfo(String notifInfo) {
		this.notifInfo = notifInfo;
	}
	public Laporan getLaporan() {
		return laporan;
	}
	public void setLaporan(Laporan laporan) {
		this.laporan = laporan;
	}
//	public Calendar getTime() {
//		return time;
//	}
//	public void setTime(Calendar time) {
//		this.time = time;
//	}
	public String getTypeNotif() {
		return typeNotif;
	}
	public void setTypeNotif(String typeNotif) {
		this.typeNotif = typeNotif;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
