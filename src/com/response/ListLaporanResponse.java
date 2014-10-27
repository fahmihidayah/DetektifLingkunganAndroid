package com.response;

import java.util.List;

import com.models.Laporan;

public class ListLaporanResponse extends BaseResponse{
	private List<Laporan> data;

	public List<Laporan> getData() {
		return data;
	}

	public void setData(List<Laporan> data) {
		this.data = data;
	}
	
}
