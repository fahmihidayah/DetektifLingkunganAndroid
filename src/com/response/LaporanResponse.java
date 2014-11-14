package com.response;

import com.models.Laporan;

public class LaporanResponse extends BaseResponse{
	private Laporan data;

	public Laporan getData() {
		return data;
	}

	public void setData(Laporan data) {
		this.data = data;
	}
	

}
