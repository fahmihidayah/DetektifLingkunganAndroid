package com.response;

import com.models.User;

/*
{
  "status": "200",
  "message": "success",
  "data": {
    "authKey": "fbbb5ac3-baac-4a17-9d9f-a144706aad3a",
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
    }
  }
}
 */
public class LoginResponse extends BaseResponse {
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {
		private String authKey;
		private User user;
		public String getAuthKey() {
			return authKey;
		}

		public void setAuthKey(String authKey) {
			this.authKey = authKey;
		}
		
		public User getUser() {
			return user;
		}
		
	}
	
}
