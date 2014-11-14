package com.detektiflingkuganandroid;

import com.engine.MainEngine;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.Laporan;
import com.models.LaporanHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

public class MainFragment extends Fragment implements Constantstas,
		OnRefreshListener {

	private MainEngine mainEngine;

	public View rootView;
	private static final int SELECT_PICTURE = 1;
	private static int TAKE_PICTURE = 5;
	public static String FIRST = "f";
	public static String NEWEST = "h";
	public static String OLDEST = "l";

	private String selectedImagePath = "";
	public SwipeRefreshLayout mSwipeRefreshLayout;
	public ListView listViewLaporan;
	public CustomAdapter<LaporanHelper> customAdapter;

	private void initialComponent() {
		mSwipeRefreshLayout = (SwipeRefreshLayout) rootView
				.findViewById(R.id.swipe_container);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		mainEngine = new MainEngine(this);

		listViewLaporan = (ListView) rootView
				.findViewById(R.id.listViewLaporan);
		customAdapter = new CustomAdapter<LaporanHelper>(getActivity(),
				R.layout.laporan_item_layout_1, mainEngine.getListLaporan()) {

			DisplayImageOptions displayImageOptions;
			ImageLoader imageLoader;

			@Override
			public void initialComponent() {
				displayImageOptions = new DisplayImageOptions.Builder()
						.showImageOnLoading(R.drawable.ic_profile_menu)
						.showImageForEmptyUri(R.drawable.ic_profile_menu)
						.showImageOnFail(R.drawable.ic_profile_menu)
						.cacheInMemory(true).cacheOnDisk(true)
						.considerExifParams(true)
						.build();
				imageLoader = ImageLoader.getInstance();
				imageLoader.init(ImageLoaderConfiguration
						.createDefault(getContext()));
				super.initialComponent();
			}

			@Override
			public void setViewItems(View view, final LaporanHelper data) {
				ImageView imageViewImageLaporan = (ImageView) view
						.findViewById(R.id.imageViewImageLaporan);
				ImageView imageViewUserProfile = (ImageView) view
						.findViewById(R.id.imageViewProfile);

				imageLoader.displayImage(data.getImagePath().getUrlImange(),
						imageViewImageLaporan, displayImageOptions);
				imageLoader.displayImage(data.getUser().getImageProfilePath().getUrlImange(),
						imageViewUserProfile, displayImageOptions);
				ViewSetterUtilities.setTextToView(view, R.id.textViewName, data.getUser().getName());
				ViewSetterUtilities.setTextToView(view, R.id.textViewDataLaporan, data.getDataLaporan());
				// jumlah lihat
				//ViewSetterUtilities.setTextToView(view, R.id.textViewDataLaporan, data.getDataLaporan());
				ViewSetterUtilities.setTextToView(view, R.id.textViewJumlahComment, data.getJumlahKomentar() + "");
				ViewSetterUtilities.setTextToView(view, R.id.textViewJumlahPatau, data.getJumlahUserPemantau() + "");
				ViewSetterUtilities.setTextToView(view, R.id.textViewJumlahLihat, data.getViewer() +"");
				ViewSetterUtilities.getImageView(view, R.id.imageButtonShare)
					.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							
						}
					});
				ViewSetterUtilities.getImageView(view, R.id.imageButtonLihat)
				.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						((HomeActivity)getActivity()).setFragment(new DetailLaporanFragment(data), true);
					}
				});
				ViewSetterUtilities.getImageView(view, R.id.imageButtonComment)
				.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
					}
				});
				
				final ImageButton imageButtonPantau = (ImageButton) ViewSetterUtilities.getImageView(view, R.id.imageButtonPantau);
				if(data.isPantau()){
					imageButtonPantau.setImageResource(R.drawable.ic_pantau_act);
				}
				else {
					imageButtonPantau.setImageResource(R.drawable.ic_pantau);
				}
				imageButtonPantau.setOnClickListener(new OnClickPantau(data));
				
				
			}
		};
		listViewLaporan.setAdapter(customAdapter);

		listViewLaporan.setOnScrollListener(new AbsListView.OnScrollListener() {
			private int preLast;

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				switch (view.getId()) {
				case android.R.id.list:

					// Make your calculation stuff here. You have all your
					// needed info from the parameters of this function.

					// Sample calculation to determine if the last
					// item is fully visible.
					final int lastItem = firstVisibleItem + visibleItemCount;
					if (lastItem == totalItemCount) {
						mainEngine.requestLaporan(OLDEST);
						// if(preLast!=lastItem){ //to avoid multiple calls for
						// last item
						//
						// preLast = lastItem;
						// }
					}
				}
			}
		});
		mainEngine.requestLaporan(FIRST);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.main_activity, null);
		initialComponent();
		return rootView;
	}

	@Override
	public void onRefresh() {
		mainEngine.requestLaporan(NEWEST);
	}

	
	/**
	 * inner class
	 */
	
	public class OnClickPantau implements View.OnClickListener {
		
		LaporanHelper data;
		public OnClickPantau(LaporanHelper data) {
			super();
			this.data = data;
		}
		@Override
		public void onClick(View v) {
			if(data.isPantau()){
				mainEngine.pantau(data, API_UNPANTAU);
			}
			else {
				mainEngine.pantau(data, API_PANTAU);
			}
		}
	}
}
