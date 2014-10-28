package com.response;

import com.models.User;

public class UserResponse extends BaseResponse{
	private User data;

	public User getData() {
		return data;
	}

	public void setData(User data) {
		this.data = data;
	}
	
}
