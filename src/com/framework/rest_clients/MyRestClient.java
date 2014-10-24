package com.framework.rest_clients;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.models.DataSingleton;

public class MyRestClient {
		// ini diisi alamat ip
	  //private static final String BASE_URL = "http://localhost/my_chat/";
//	private static final String BASE_URL = "http://192.168.1.6/ip_url/";
	private static final String HEADER = "http://";
	private static final String PATH = "/api/test/";
	
	
	  private static AsyncHttpClient client = new AsyncHttpClient();

	  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.get(getAbsoluteUrl(url), params, responseHandler);
	  }

	  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
	      client.post(getAbsoluteUrl(url), params, responseHandler);
	  }

	  private static String getAbsoluteUrl(String relativeUrl) {
	      return HEADER + DataSingleton.getInstance().getServerAddress() + PATH + relativeUrl ;
	  }
}
