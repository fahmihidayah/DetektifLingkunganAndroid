package com.response;

import com.models.Laporan;
/*
 * {
  "status": "200",
  "message": "success",
  "data": [
    {
      "id": 1,
      "judulLaporan": "Jalan Rusaaaak!!",
      "dataLaporan": "ini adalah jalan yang rusak",
      "tanggapan": null,
      "user": {
        "id": 1,
        "type": "PEMANTAU",
        "name": "fahmi",
        "email": "fahmi@rembugan.com",
        "status": "Alhamdulillah",
        "jumlahFollowerUser": 0,
        "jumlahFollowingUser": 0,
        "imageProfilePath": {
          "id": 1,
          "fileName": "6d249051_db1c_4704_8d78_d741ac02954a",
          "keterangan": "default profile",
          "urlImange": "http://192.168.1.5/uploads/6d249051_db1c_4704_8d78_d741ac02954a"
        },
        "isFollowing": false
      },
      "jumlahKomentar": 1,
      "jumlahUserPemantau": 1,
      "katagoriLaporan": "Kerusakan",
      "longitude": -7.0912121,
      "latitude": 112.01209,
      "time": 1416375205000,
      "listImagePath": [
        
      ],
      "viwer": 0,
      "pantau": true
    }
  ]
}
 */
public class LaporanResponse extends BaseResponse{
	private Laporan data;

	public Laporan getData() {
		return data;
	}

	public void setData(Laporan data) {
		this.data = data;
	}
	

}
