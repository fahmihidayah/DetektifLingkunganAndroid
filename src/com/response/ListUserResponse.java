package com.response;

import java.util.ArrayList;

import com.models.User;
import com.models.UserHelper;

public class ListUserResponse extends BaseResponse{
	private ArrayList<UserHelper> data;

	public ArrayList<UserHelper> getData() {
		return data;
	}

	public void setData(ArrayList<UserHelper> data) {
		this.data = data;
	}
	
}
