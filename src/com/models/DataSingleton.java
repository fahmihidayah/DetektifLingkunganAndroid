package com.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import android.content.Context;

import com.framework.file_handler.FileHandler;

public class DataSingleton extends Observable implements Constantstas{
	private static DataSingleton instance;

	private String serverAddress = "192.168.1.10:9000";
	private String authKey = "";
	private boolean isLogin = false;
	private User user = null;
	private List<Laporan> listDataLaporan = new ArrayList<Laporan>();

	
	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public List<Laporan> getListDataLaporan() {
		return listDataLaporan;
	}

	public void setListDataLaporan(List<Laporan> listDataLaporan) {
		this.listDataLaporan = listDataLaporan;
	}

	protected DataSingleton() {

	}

	public static DataSingleton getInstance() {
		if (instance == null) {
			instance = new DataSingleton();
		}
		return instance;
	}

	public void notifyObserverDataChange() {
		setChanged();
		notifyObservers();
	}
	
	public void saveToFile(Context context){
		FileHandler.putBooleanValue(context, IS_LOGIN, isLogin);
		FileHandler.putStringValue(context, SERVER_ADDRESS, serverAddress);
		FileHandler.putStringValue(context, AUTH_KEY, authKey);
		try {
			FileHandler.saveDataToFile(context, USER_DATA, Context.MODE_PRIVATE, user);
			FileHandler.saveDataToFile(context, LIST_LAPORAN, Context.MODE_PRIVATE, (ArrayList<Laporan>) listDataLaporan);
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	public void loadFromFile(Context context){
		isLogin = FileHandler.getBooleanValue(context, IS_LOGIN);
		serverAddress = FileHandler.getStringValue(context, SERVER_ADDRESS);
		authKey = FileHandler.getStringValue(context, AUTH_KEY);
		try {
			user = (User) FileHandler.loadDataFromFile(context, USER_DATA);
		} catch (StreamCorruptedException e) {
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			
		}
	}

}
