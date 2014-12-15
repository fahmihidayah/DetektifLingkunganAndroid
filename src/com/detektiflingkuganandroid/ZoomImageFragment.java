package com.detektiflingkuganandroid;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.engine.DetektivUtilities;
import com.models.Constantstas;
import com.models.DataSingleton;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ZoomImageFragment extends Fragment implements Constantstas {

	@InjectView(R.id.imageViewZoomImage)
	ImageView imageViewZoomImage;

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.zoom_image_activity);
	// ButterKnife.inject(this);
	//
	// String url = getIntent().getStringExtra("url");
	// DetektivUtilities detektivUtilities = new DetektivUtilities(this);
	// detektivUtilities.getImageLoader().load
	// }

	String url = "";

	public ZoomImageFragment(String url) {
		super();
		this.url = url;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.zoom_image_fragment, null);
		ButterKnife.inject(this, rootView);
		DetektivUtilities detektivUtilities = new DetektivUtilities(getActivity());
		 detektivUtilities.getImageLoader().displayImage(url, imageViewZoomImage, detektivUtilities.getDisplayImageOptionsDetail());
		return rootView;
	}

}
