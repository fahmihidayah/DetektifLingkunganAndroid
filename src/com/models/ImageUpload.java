package com.models;

import android.graphics.Bitmap;
import android.net.Uri;

public class ImageUpload {
	private int resource;
	private Uri uri;
	private boolean addImage;

	public int getResource() {
		return resource;
	}

	public void setResource(int resource) {
		this.resource = resource;
	}
	
	public boolean isAddImage() {
		return addImage;
	}

	public void setAddImage(boolean addImage) {
		this.addImage = addImage;
	}

	public Uri getUri() {
		return uri;
	}
	public void setUri(Uri uri) {
		this.uri = uri;
	}
}
