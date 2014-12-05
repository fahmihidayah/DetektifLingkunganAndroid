package com.engine;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressDialogFactory {
	ProgressDialog progress;

	public ProgressDialogFactory(Context context) {
		progress = new ProgressDialog(context);
	}
	
	public void show(String title, boolean cancelable){
		progress.setTitle(title);
		progress.setCancelable(cancelable);
		progress.show();
	}
	
	public void dismiss(){
		progress.dismiss();
	}
	
	

}
