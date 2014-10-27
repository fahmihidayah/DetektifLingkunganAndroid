package com.models;

import java.io.Serializable;
import java.util.Calendar;
/*
 {
  "status": "200",
  "message": "success",
  "data": [
    {
      "id": 1,
      "dataLaporan": "sampaaaaah",
      "tanggapan": null,
      "user": {
        "id": 1,
        "userName": "f",
        "password": "f",
        "type": "PEMANTAU",
        "name": "fahmi",
        "email": "fahmi@rembugan.com",
        "status": "i'm a detective",
        "jumlahFollowerUser": 0,
        "jumlahFollowingUser": 0
      },
      "jumlahKomentar": 0,
      "jumlahUserPemantau": 0,
      "katagoriLaporan": "Sampah",
      "longitude": -7.0912121,
      "latitude": 112.01209,
      "time": 1414164495000
    },
    {
      "id": 2,
      "dataLaporan": "bauuuu",
      "tanggapan": null,
      "user": {
        "id": 1,
        "userName": "f",
        "password": "f",
        "type": "PEMANTAU",
        "name": "fahmi",
        "email": "fahmi@rembugan.com",
        "status": "i'm a detective",
        "jumlahFollowerUser": 0,
        "jumlahFollowingUser": 0
      },
      "jumlahKomentar": 0,
      "jumlahUserPemantau": 0,
      "katagoriLaporan": "Sampah",
      "longitude": -7.0912121,
      "latitude": 112.01209,
      "time": 1414164502000
    }
  ]
}
 */
public class Laporan  implements Serializable{
	private Long id;
	private String dataLaporan;
	private Tanggapan tanggapan;
	private User user;
	private Integer jumlahKomentar;
	private Integer jumlahUserPemantau;
	private String katagoriLaporan;
	private Double longitude;
	private Double latitude;
	private String time;
	
	private boolean pantau;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
}
