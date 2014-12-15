package com.detektiflingkuganandroid;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.Constantstas;
import com.models.DataSingleton;
import com.models.Laporan;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class MainActivity extends FragmentActivity implements Constantstas/*, OnRefreshListener*/{
//
//	private static final int SELECT_PICTURE = 1;
//	private static int TAKE_PICTURE = 5;
//	public static String FIRST = "f";
//	public static String NEWEST = "h";
//	public static String OLDEST = "l";
//	
//	private String selectedImagePath = "";
//	private MainEngine mainEngine;
//	public SwipeRefreshLayout mSwipeRefreshLayout;
//	public ListView listViewLaporan;
//	public CustomAdapter<Laporan> customAdapter;
//	
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        setContentView(R.layout.main_activity);
//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
//        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mainEngine = new MainEngine(this);
//        
//        View customActionBar = getLayoutInflater().inflate(R.layout.custom_main_action_bar, null);
//        getActionBar().setDisplayShowHomeEnabled(false);
//        getActionBar().setDisplayShowTitleEnabled(false);
//        getActionBar().setDisplayShowCustomEnabled(true);
//        getActionBar().setCustomView(customActionBar);
//        
//        listViewLaporan = (ListView) findViewById(R.id.listViewLaporan);
//        customAdapter = new CustomAdapter<Laporan>(this, R.layout.laporan_item_layout, mainEngine.getListLaporan()) {
//			
//			@Override
//			public void setViewItems(View view, final Laporan data) {
//				
//				ViewSetterUtilities.setTextToView(view, R.id.textViewDataLaporan, data.getDataLaporan());
//				
//				ViewSetterUtilities.getImageButton(view, R.id.imageButtonLocation)
//					.setOnClickListener(new View.OnClickListener() {
//						
//						@Override
//						public void onClick(View v) {
//							Intent intent = new Intent(MainActivity.this, LaporanMapActivity.class);
//							intent.putExtra("long", data.getLongitude());
//							intent.putExtra("lat", data.getLatitude());
//							MainActivity.this.startActivity(intent);
//						}
//					});
//				
//				ViewSetterUtilities.getImageButton(view, R.id.imageButtonPantau)
//					.setImageResource(data.isPantau()? R.drawable.ic_pantau_active : R.drawable.ic_pantau_passive);
//				
//				ViewSetterUtilities.getImageButton(view, R.id.imageButtonPantau)
//					.setOnClickListener(new View.OnClickListener(){
//
//						@Override
//						public void onClick(View v) {
//							mainEngine.pantau(data);
//						}						
//					});
//				
//				ViewSetterUtilities.getImageButton(view, R.id.imageButtonComment)
//					.setOnClickListener(new View.OnClickListener() {
//						
//						@Override
//						public void onClick(View v) {
//							mainEngine.showDetailLaporan(data);
//						}
//					});
//				new ImageDownloader(ViewSetterUtilities.getImageView(view, R.id.imageViewImageLaporan)).execute(data.getImagePath().getUrlImange());
//				new ImageDownloader(ViewSetterUtilities.getImageView(view, R.id.imageViewProfile)).execute(data.getUser().getImageProfilePath().getUrlImange());
//				ViewSetterUtilities.setTextToView(view, R.id.textViewName, data.getUser().getName());
//				ViewSetterUtilities.setTextToView(view, R.id.textViewKeterangan, "Pemantau : " + data.getJumlahUserPemantau()+ " Komentar : " + data.getJumlahKomentar());
//			}
//		};
//		listViewLaporan.setAdapter(customAdapter);
//		
//		listViewLaporan.setOnScrollListener(new AbsListView.OnScrollListener() {
//			private int preLast;
//			@Override
//			public void onScrollStateChanged(AbsListView view, int scrollState) {
//				
//			}
//			
//			@Override
//			public void onScroll(AbsListView view, int firstVisibleItem,
//					int visibleItemCount, int totalItemCount) {
//				switch(view.getId()) {
//		        case android.R.id.list:     
//
//		            // Make your calculation stuff here. You have all your
//		            // needed info from the parameters of this function.
//
//		            // Sample calculation to determine if the last 
//		            // item is fully visible.
//		             final int lastItem = firstVisibleItem + visibleItemCount;
//		           if(lastItem == totalItemCount) {
//		        	   mainEngine.requestLaporan(OLDEST);
////		              if(preLast!=lastItem){ //to avoid multiple calls for last item
////		                
////		                preLast = lastItem;
////		              }
//		           }
//				}
//			}
//		});
//		mainEngine.requestLaporan(FIRST);
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onRefresh() {
//    	mainEngine.requestLaporan(NEWEST);
//    }
//    
//    @Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.logout_menu, menu);
//		return true;
//	}
//	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case R.id.logout:
//			mainEngine.logout();
//			break;
//
//		case R.id.lapor :
//			MenuPictureDialog menuPictureDialog = new MenuPictureDialog();
//			menuPictureDialog.show(getSupportFragmentManager(), "menu_pict");
//			break;
//		case R.id.profile :
//			Intent intent = new Intent(this, DetailUserProfileActivity.class);
//			intent.putExtra("own_profile", true);
//			intent.putExtra("user", DataSingleton.getInstance().getUser());
//			startActivity(intent);
//			break;
//		default:
//			return super.onOptionsItemSelected(item);
//		}
//		return true;
//	}
//	
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		Intent intent = new Intent(this, LaporActivity.class);
//		Toast.makeText(this, "req code " + resultCode, Toast.LENGTH_LONG).show();
////		if (requestCode == SELECT_PICTURE  && null != data) {
//		if (requestCode == 65541 && data != null){
//			Bundle extras = data.getExtras();
//            
//            intent.putExtras(extras);
//            
//		}
//		if(requestCode == 65537 && data != null){
//			 Uri selectedImage = data.getData();
//	         String[] filePathColumn = { MediaStore.Images.Media.DATA };
//	         Cursor cursor = getContentResolver().query(selectedImage,
//	                    filePathColumn, null, null, null);
//	         cursor.moveToFirst();
//	 
//	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//	            String picturePath = cursor.getString(columnIndex);
//	            cursor.close();
//	            
//	            intent.putExtra("image_path", picturePath);
//		}
//		startActivity(intent);	
//        
//    }
//	
//	public String getPath(Uri uri) {
//        // just some safety built in 
//        if( uri == null ) {
//            // TODO perform some logging or show user feedback
//            return null;
//        }
//        // try to retrieve the image from the media store first
//        // this will only work for images selected from gallery
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//        if( cursor != null ){
//            int column_index = cursor
//            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            return cursor.getString(column_index);
//        }
//        // this is our fallback here
//        return uri.getPath();
//}
//	
//	public class MenuPictureDialog extends DialogFragment{
//		@Override
//		public Dialog onCreateDialog(Bundle savedInstanceState) {
//			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//		    builder
//		           .setItems(R.array.menu_gambar, new DialogInterface.OnClickListener() {
//		               public void onClick(DialogInterface dialog, int which) {
//		            	   switch (which) {
//						case 0:
//							Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//							 
//					        // start camera activity
//					        startActivityForResult(intent, TAKE_PICTURE);
//							break;
//						case 1:
////							Intent intent = new Intent();
////	                        intent.setType("image/*");
////	                        intent.setAction(Intent.ACTION_GET_CONTENT);
////	                        startActivityForResult(Intent.createChooser(intent,
////	                                "Select Picture"), SELECT_PICTURE);
//							  Intent i = new Intent(
//				                        Intent.ACTION_PICK,
//				                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//				                 
//				                startActivityForResult(i, SELECT_PICTURE);
//							break;
//						case 2:
//							break;
//						default:
//							break;
//						}
//		           }
//		    });
//		    return builder.create();
//		}
//		
//	}
//	

}
