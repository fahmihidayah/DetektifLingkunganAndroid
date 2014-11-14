package com.models;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class LaporanHelper extends Laporan{
	private Bitmap laporanBitmapImage;
	private Bitmap userBitmapImage;

	public Bitmap getLaporanBitmpImage() {
		return laporanBitmapImage;
	}

	public void setLaporanBitmapImage(Bitmap bitmap) {
		this.laporanBitmapImage = bitmap;
	}
	
	public void setLaporanBitmapImage(ImageView imageView){
		this.laporanBitmapImage = getBitmapImageView(imageView);
	}

	public Bitmap getUserBitmapImage() {
		return userBitmapImage;
	}

	public void setUserBitmapImage(Bitmap userBitmapImage) {
		this.userBitmapImage = userBitmapImage;
	}
	public void setUserBitmapImage(ImageView imageView) {
		this.userBitmapImage = getBitmapImageView(imageView);
	}
	
	private Bitmap getBitmapImageView(ImageView imageView){
		return ((BitmapDrawable) imageView.getDrawable()).getBitmap();
	}
}
