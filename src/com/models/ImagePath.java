package com.models;

import java.io.Serializable;

public class ImagePath implements Serializable{
	private Long id;
	private String path;
	private String katerangan;
	private String urlImange;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
