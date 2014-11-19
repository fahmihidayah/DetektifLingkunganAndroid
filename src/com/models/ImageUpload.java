package com.models;

import android.graphics.Bitmap;

public class ImageUpload {
	private int resource;
	private String path;
	private boolean addImage;

	public int getResource() {
		return resource;
	}

	public void setResource(int resource) {
		this.resource = resource;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isAddImage() {
		return addImage;
	}

	public void setAddImage(boolean addImage) {
		this.addImage = addImage;
	}

}
