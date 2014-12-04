package com.adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.detektiflingkuganandroid.R;
import com.models.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class CustomInjectedAdapter<T> extends BaseAdapter {

	protected List<T> listData;
	protected Context context;

	public CustomInjectedAdapter(List<T> listData, Context context) {
		super();
		this.listData = listData;
		this.context = context;
		initialComponent();
	}

	public void initialComponent(){
		
	}
	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public T getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		ViewHolder holder;
//		if (convertView != null) {
//			holder = (ViewHolder) convertView.getTag();
//		} else {
//			LayoutInflater inflater = (LayoutInflater) context
//					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			convertView = inflater.inflate(R.layout.user_item_layout,
//					parent, false);
//			holder = new ViewHolder(convertView);
//			convertView.setTag(holder);
//		}
//		T data = listData.get(position);
//		fillViewHolder(data, holder);
//		return convertView;
//	}
	
//	public static class ViewHolder {
//
//		public ViewHolder(View view) {
//			ButterKnife.inject(this, view);
//		}
//	}

}
