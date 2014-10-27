package com.detektiflingkuganandroid;

import com.engine.DetailLaporanEngine;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.Komentar;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class DetailLaporanActivity extends FragmentActivity implements Constantstas{

	public ListView listViewKomentar;
	public CustomAdapter<Komentar> customAdapter;
	private DetailLaporanEngine detailLaporanEngine;
	
	private void initialComponent(){
		detailLaporanEngine = new DetailLaporanEngine(this);
		listViewKomentar = (ListView) findViewById(R.id.listViewKomentar);
		customAdapter = new CustomAdapter<Komentar>(this, R.layout.komentar_item_layout, detailLaporanEngine.getListKomentar()) {
			
			@Override
			public void setViewItems(View view, Komentar data) {
				ViewSetterUtilities.setTextToView(view, R.id.textViewUserKomentar, data.getUser().getName());
				ViewSetterUtilities.setTextToView(view, R.id.textViewKomentarUser, data.getDataKomentar());
			}
		};
		listViewKomentar.setAdapter(customAdapter);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_laporan_activity);
		initialComponent();
		detailLaporanEngine.requestListKomentar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
