package com.detektiflingkuganandroid;

import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.Komentar;
import com.models.Laporan;
import com.models.LaporanHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class DetailLaporanFragment extends Fragment {

	public ImageView imageViewProfile, imageViewLaporan;
	public TextView textViewUserName, textViewDataLaporan, textViewTime;
	public ListView listViewKomentar;
	public Button buttonComment, buttonPantau;
	public CustomAdapter<Komentar> customAdapter;
	public View rootView;
	public LaporanHelper laporan;

	DisplayImageOptions displayImageOptionsProfile, displayImageOptionsLaporan;
	ImageLoader imageLoader;

	public DetailLaporanFragment(LaporanHelper laporan) {
		this.laporan = laporan;
	}

	private void initialComponent() {
		displayImageOptionsProfile = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_profile_menu)
				.showImageForEmptyUri(R.drawable.ic_profile_menu)
				.showImageOnFail(R.drawable.ic_profile_menu)
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20))
				.build();
		displayImageOptionsLaporan = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_profile_menu)
		.showImageForEmptyUri(R.drawable.ic_profile_menu)
		.showImageOnFail(R.drawable.ic_profile_menu)
		.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
		.build();
		
		View customActionBar = getActivity().getLayoutInflater().inflate(
				R.layout.custom_detail_laporan_action_bar, null);
		getActivity().getActionBar().setDisplayShowHomeEnabled(false);
		getActivity().getActionBar().setDisplayShowTitleEnabled(false);
		getActivity().getActionBar().setDisplayShowCustomEnabled(true);
		getActivity().getActionBar().setCustomView(customActionBar);
		ViewSetterUtilities.getImageView(customActionBar, R.id.imageButtonBack)
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						getActivity().onBackPressed();
					}
				});
		
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
		imageViewProfile = (ImageView) rootView
				.findViewById(R.id.imageViewProfile);
		imageViewLaporan = (ImageView) rootView
				.findViewById(R.id.imageViewImageLaporan);
		buttonComment = (Button) rootView.findViewById(R.id.buttonComment);
		buttonPantau = (Button) rootView.findViewById(R.id.buttonPantau);
		textViewUserName = (TextView) rootView.findViewById(R.id.textViewName);
		textViewDataLaporan = (TextView) rootView
				.findViewById(R.id.textViewDataLaporan);
		textViewTime = (TextView) rootView.findViewById(R.id.textViewTime);
		listViewKomentar = (ListView) rootView
				.findViewById(R.id.listViewKomentar);
		
		textViewUserName.setText(laporan.getUser().getName());
		textViewDataLaporan.setText(laporan.getDataLaporan());
		imageLoader.displayImage(laporan.getUser().getImageProfilePath().getUrlImange(), imageViewProfile, displayImageOptionsProfile);
		imageLoader.displayImage(laporan.getImagePath().getUrlImange(), imageViewLaporan, displayImageOptionsLaporan);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.detail_laporan_fragment, null);
		initialComponent();
		return rootView;
	}

}
