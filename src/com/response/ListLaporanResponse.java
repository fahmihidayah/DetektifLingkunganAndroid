package com.response;

import java.util.List;

import com.models.Laporan;
import com.models.LaporanHelper;

public class ListLaporanResponse extends BaseResponse{
	private List<LaporanHelper> data;

	public List<LaporanHelper> getData() {
		return data;
	}

	public void setData(List<LaporanHelper> data) {
		this.data = data;
	}
	
}
