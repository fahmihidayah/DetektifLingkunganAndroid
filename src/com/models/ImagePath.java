package com.models;

import java.io.Serializable;

import com.orm.SugarRecord;
/*
 * {
        "id": 1,
        "fileName": "6d249051_db1c_4704_8d78_d741ac02954a",
        "keterangan": "default profile",
        "urlImange": "http://192.168.1.5/uploads/6d249051_db1c_4704_8d78_d741ac02954a"
      }
 */
public class ImagePath extends SugarRecord<ImagePath> implements Serializable{
	public Long idImagePath;
	public String fileName;
	public String katerangan;
	public String urlImange;
	
	public ImagePath() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdImagePath() {
		return idImagePath;
	}
	public void setIdImagePath(Long idImagePath) {
		this.idImagePath = idImagePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getKaterangan() {
		return katerangan;
	}
	public void setKaterangan(String katerangan) {
		this.katerangan = katerangan;
	}
	public String getUrlImange() {
		return urlImange;
	}
	public void setUrlImange(String urlImange) {
		this.urlImange = urlImange;
	}
	

}
