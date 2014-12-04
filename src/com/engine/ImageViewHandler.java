package com.engine;

import android.content.Context;

import com.detektiflingkuganandroid.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImageViewHandler {
	DisplayImageOptions displayImageOptionsProfile;
	DisplayImageOptions displayImageOptionsDetail;
	ImageLoader imageLoader;

	public ImageViewHandler(Context context){
		initialComponent(context);
	}
	public void initialComponent(Context context) {
		displayImageOptionsProfile = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_profile_menu)
				.showImageForEmptyUri(R.drawable.ic_profile_menu)
				.showImageOnFail(R.drawable.ic_profile_menu)
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();
		displayImageOptionsDetail = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_profile_menu)
				.showImageForEmptyUri(R.drawable.ic_profile_menu)
				.showImageOnFail(R.drawable.ic_profile_menu)
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
				.build();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
	}

	public DisplayImageOptions getDisplayImageOptionsDetail() {
		return displayImageOptionsDetail;
	}

	public DisplayImageOptions getDisplayImageOptionsProfile() {
		return displayImageOptionsProfile;
	}

	public ImageLoader getImageLoader() {
		return imageLoader;
	}
}
