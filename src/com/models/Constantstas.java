package com.models;

import android.app.Activity;

public interface Constantstas {
	public static int DELAYED_SPLASH_SCREEN_TIME = 1000;

	public static String LOGIN_STATE = "LOGIN_STATE";
	public static String ACTIVE_STATE = "ACTIVE_STATE";
	public static String AUTH_KEY =	"AUTH_KEY";
	public static String SERVER_ADDRESS = "SERVER_ADDRESS";
	public static String DATE_FROMAT = "EEEE, MMM dd, yyyy HH:mm:ss a";
	
	public static String SHARED_PREFERENCE_FILE = "SH_F";
	public static int MODE = Activity.MODE_PRIVATE;
	
	public static String MODE_FOLLOWER = "follower";
	public static String MODE_FOLLOWING = "following";
	
	public static String IS_LOGIN = "IS_LOGIN";
	public static String USER_DATA = "USER_DATA.DAT";
	public static String LIST_LAPORAN = "LIST_LAPORAN.DAT";
	
	public static String API_LOGIN = "login";
	public static String API_DAFTAR = "register";
	public static String API_LOGOUT = "logout";
	public static String API_LIST_LAPORAN = "get_list_laporan";
	public static String API_PANTAU = "pantau";
	public static String API_UNPANTAU = "unpantau";
	public static String API_LIST_KOMENTAR = "list_komentar";
	public static String API_INSERT_KOMENTAR = "insert_komentar";
	public static String API_USER_DETAIL = "user_detail";
	public static String API_FOLLOW = "follow";
	public static String API_UNFOLLOW = "unfollow";
	public static String API_UPDATE_STATUS = "update_status";
	public static String API_INSERT_LAPORAN = "insert_laporan";
	public static String API_GET_FOLLOWER = "get_follower";
	public static String API_VIEW = "view";
	
}
