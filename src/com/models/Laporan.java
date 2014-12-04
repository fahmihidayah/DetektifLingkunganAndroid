package com.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;

/*
 {
 "status": "200",
 "message": "success",
 "data": [
 {
 "id": 1,
 "judulLaporan": "Jalan Rusaaaak!!",
 "dataLaporan": "ini adalah jalan yang rusak",
 "tanggapan": null,
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
 },
 "jumlahKomentar": 1,
 "jumlahUserPemantau": 1,
 "katagoriLaporan": "Kerusakan",
 "longitude": -7.0912121,
 "latitude": 112.01209,
 "time": 1416375205000,
 "listImagePath": [

 ],
 "viwer": 0,
 "pantau": true
 }
 ]
 }
 */
public class Laporan implements Serializable {
	private Long idLaporan;
	private String judulLaporan;
	private String dataLaporan;
	private Tanggapan tanggapan;
	private User user;
	private Integer jumlahKomentar;
	private Integer jumlahUserPemantau;
	private String katagoriLaporan;
	private Double longitude;
	private Double latitude;
	private String time;
	private ArrayList<ImagePath> listImagePath;
	private Integer viwer;

	private boolean pantau;

	
	public Long getIdLaporan() {
		return idLaporan;
	}

	public void setIdLaporan(Long idLaporan) {
		this.idLaporan = idLaporan;
	}

	public String getDataLaporan() {
		return dataLaporan;
	}

	public void setDataLaporan(String dataLaporan) {
		this.dataLaporan = dataLaporan;
	}

	public Tanggapan getTanggapan() {
		return tanggapan;
	}

	public void setTanggapan(Tanggapan tanggapan) {
		this.tanggapan = tanggapan;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getJumlahKomentar() {
		return jumlahKomentar;
	}

	public void setJumlahKomentar(Integer jumlahKomentar) {
		this.jumlahKomentar = jumlahKomentar;
	}

	public Integer getJumlahUserPemantau() {
		return jumlahUserPemantau;
	}

	public void setJumlahUserPemantau(Integer jumlahUserPemantau) {
		this.jumlahUserPemantau = jumlahUserPemantau;
	}

	public String getKatagoriLaporan() {
		return katagoriLaporan;
	}

	public void setKatagoriLaporan(String katagoriLaporan) {
		this.katagoriLaporan = katagoriLaporan;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean isPantau() {
		return pantau;
	}

	public void setPantau(boolean pantau) {
		this.pantau = pantau;
	}

	public String getJudulLaporan() {
		return judulLaporan;
	}

	public void setJudulLaporan(String judulLaporan) {
		this.judulLaporan = judulLaporan;
	}

	public ArrayList<ImagePath> getListImagePath() {
		return listImagePath;
	}

	public void setListImagePath(ArrayList<ImagePath> listImagePath) {
		this.listImagePath = listImagePath;
	}

	public Integer getViwer() {
		return viwer;
	}

	public void setViwer(Integer viwer) {
		this.viwer = viwer;
	}

	public Integer getViewer() {
		return viwer;
	}

	public void setViewer(Integer viwer) {
		this.viwer = viwer;
	}

}
