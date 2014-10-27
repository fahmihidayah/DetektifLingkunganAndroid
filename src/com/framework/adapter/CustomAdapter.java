package com.framework.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

public abstract class CustomAdapter<T> extends BaseAdapter{

	private Context contextMain;
	private int resourceId;
	protected List<T> listData;

	public CustomAdapter(Context context, int textViewResourceId,
			List<T> listData) {
		super();
		// TODO Auto-generated constructor stub
		this.contextMain = context;
		this.resourceId = textViewResourceId;
		this.listData = listData;
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	public LayoutInflater getLayoutInflater() {
		return (LayoutInflater) contextMain
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = getLayoutInflater();
//		if(convertView == null){
			View customView = inflater.inflate(resourceId, null);
			setViewItems(customView, listData.get(position));
			convertView = customView;
//		}
		
		return convertView;
	}

	public abstract void setViewItems(View view, T data);

	public Context getContext() {
		return contextMain;
	}

	public void setContext(Context context) {
		this.contextMain = context;
	}

	public List<T> getListData() {
		return listData;
	}

	public void setListData(List<T> listData) {
		this.listData = listData;
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
