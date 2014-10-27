package com.response;

import java.util.ArrayList;

import com.models.Komentar;

public class ListKomentarResponse extends BaseResponse{
	private ArrayList<Komentar> data;

	public ArrayList<Komentar> getData() {
		return data;
	}

	public void setData(ArrayList<Komentar> data) {
		this.data = data;
	}
	

}
