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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class DetailLaporanActivity extends FragmentActivity implements Constantstas{

	public ListView listViewKomentar;
	public CustomAdapter<Komentar> customAdapter;
	public EditText editTextComment;
	public ImageButton imageButtonComment;
	private DetailLaporanEngine detailLaporanEngine;
	
	private void initialComponent(){
//		detailLaporanEngine = new DetailLaporanEngine(this);
		listViewKomentar = (ListView) findViewById(R.id.listViewKomentar);
		customAdapter = new CustomAdapter<Komentar>(this, R.layout.komentar_item_layout, detailLaporanEngine.getListKomentar()) {
			
			@Override
			public void setViewItems(View view, Komentar data) {
				new ImageDownloader(ViewSetterUtilities.getImageView(view, R.id.imageViewProfile)).execute(data.getUser().getImageProfilePath().getUrlImange());
				ViewSetterUtilities.setTextToView(view, R.id.textViewUserKomentar, data.getUser().getName());
				ViewSetterUtilities.setTextToView(view, R.id.textViewKomentarUser, data.getDataKomentar());
				
			}
		};
		listViewKomentar.setAdapter(customAdapter);
		listViewKomentar.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				detailLaporanEngine.showFriendProfile(arg2);
			}
		});
		imageButtonComment = (ImageButton) findViewById(R.id.imageButtonComment);
		editTextComment = (EditText) findViewById(R.id.editTextComment);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_laporan_activity);
		initialComponent();
		detailLaporanEngine.requestListKomentar();
	}

	public void onClickComment(View view){
		detailLaporanEngine.comment(editTextComment.getText().toString());
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

}
