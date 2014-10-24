package com.response;

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

		public String getAuthKey() {
			return authKey;
		}

		public void setAuthKey(String authKey) {
			this.authKey = authKey;
		}
		
	}
	
}
