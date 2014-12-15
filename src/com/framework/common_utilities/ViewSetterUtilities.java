package com.framework.common_utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewSetterUtilities {
	public static TextView setTextToView(View rootView,int resourceTextView, String text){
		TextView textView = (TextView) rootView.findViewById(resourceTextView);
		Log.d("Result", "this is " + text);
		textView.setText(text);
		return textView;
	}
	
	public static Button getButton(View rootView, int resource){
		return(Button) rootView.findViewById(resource);
	}
	
	public static ImageButton getImageButton(View rootView, int resource){
		return(ImageButton) rootView.findViewById(resource);
	}
	
	public static ImageView getImageView(View rootView, int resource){
		return (ImageView) rootView.findViewById(resource);
	}
	
	public static String getStringDate(Date date, String format){
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(date);
	}
	
	public static ImageView setResToImageView(View rootView, int imageViewResource,int imageResource){
		ImageView imageView = (ImageView) rootView.findViewById(imageViewResource);
		imageView.setImageResource(imageResource);
		return  imageView;
	}
	
	public static ImageView setPathToImageView(View rootView, int imageViewResource, String path){
		File file = new  File(path);
		ImageView imageView = (ImageView) rootView.findViewById(imageViewResource);
		if(file.exists()){
			Bitmap bitmapImage = BitmapFactory.decodeFile(file.getAbsolutePath());
			imageView.setImageBitmap(bitmapImage);
		}
		return imageView;
	}
	
	public static ImageView setPathToImageView(View rootView, int imageViewResource, String path, BitmapFactory.Options options){
		File file = new  File(path);
		ImageView imageView = (ImageView) rootView.findViewById(imageViewResource);
		if(file.exists()){
			Bitmap bitmapImage = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
			imageView.setImageBitmap(bitmapImage);
		}
		return imageView;
	}
	
	public static ImageView setUriToImageView(View rootView, int imageViewResource, Uri uri){
		ImageView imageView = (ImageView) rootView.findViewById(imageViewResource);
		imageView.setImageURI(uri);
		return imageView;
	}
	
}
