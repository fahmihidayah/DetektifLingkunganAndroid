package com.detektiflingkuganandroid;

import com.engine.MainEngine;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.Laporan;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements Constantstas, OnRefreshListener{

	public static String FIRST = "f";
	public static String NEWEST = "h";
	public static String OLDEST = "l";
	
	private MainEngine mainEngine;
	public SwipeRefreshLayout mSwipeRefreshLayout;
	public ListView listViewLaporan;
	public CustomAdapter<Laporan> customAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.main_activity);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mainEngine = new MainEngine(this);
        listViewLaporan = (ListView) findViewById(R.id.listViewLaporan);
        customAdapter = new CustomAdapter<Laporan>(this, R.layout.laporan_item_layout, mainEngine.getListLaporan()) {
			
			@Override
			public void setViewItems(View view, final Laporan data) {
				
				ViewSetterUtilities.setTextToView(view, R.id.textViewDataLaporan, data.getDataLaporan());
				ViewSetterUtilities.getImageButton(view, R.id.imageButtonLocation)
					.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							Intent intent = new Intent(MainActivity.this, LaporanMapActivity.class);
							intent.putExtra("long", data.getLongitude());
							intent.putExtra("lat", data.getLatitude());
							MainActivity.this.startActivity(intent);
						}
					});
				
				ViewSetterUtilities.getImageButton(view, R.id.imageButtonPantau)
					.setImageResource(data.isPantau()? R.drawable.pantau : R.drawable.unpantau);
				
				ViewSetterUtilities.getImageButton(view, R.id.imageButtonPantau)
					.setOnClickListener(new View.OnClickListener(){

						@Override
						public void onClick(View v) {
							mainEngine.pantau(data);
						}						
					});
				
				ViewSetterUtilities.getImageButton(view, R.id.imageButtonComment)
					.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							mainEngine.showDetailLaporan(data);
						}
					});
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
				switch(view.getId()) {
		        case android.R.id.list:     

		            // Make your calculation stuff here. You have all your
		            // needed info from the parameters of this function.

		            // Sample calculation to determine if the last 
		            // item is fully visible.
		             final int lastItem = firstVisibleItem + visibleItemCount;
		           if(lastItem == totalItemCount) {
		        	   mainEngine.requestLaporan(OLDEST);
//		              if(preLast!=lastItem){ //to avoid multiple calls for last item
//		                
//		                preLast = lastItem;
//		              }
		           }
				}
			}
		});
		mainEngine.requestLaporan(FIRST);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRefresh() {
    	mainEngine.requestLaporan(NEWEST);
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logout_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logout:
			mainEngine.logout();
			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

}
