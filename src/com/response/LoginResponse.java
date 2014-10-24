package com.response;

import com.models.User;

/*
 * {
  "status": "200",
  "message": "success",
  "data": {
    "authKey": "ece02ca5-db32-42d7-a00e-da5217baf275",
    {
  "status": "200",
  "message": "success",
  "data": {
    "authKey": "42961a23-9e42-41a1-abe0-fda800bfce6e",
    "user": {
      "id": 2,
      "userName": "f",
      "password": "f",
      "type": "PEMANTAU",
      "name": "fahmi",
      "email": "fahmi@rembugan.com",
      "status": "i'm a detective"
    }
  }
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
