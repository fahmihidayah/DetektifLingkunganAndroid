package com.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import android.content.Context;

import com.framework.file_handler.FileHandler;
import com.orm.query.Select;

public class DataSingleton extends Observable implements Constantstas{
	private static DataSingleton instance;

	private String serverAddress = "192.168.1.8:9000";
	//http://128.199.207.197:9000
	private String authKey = "";
	private boolean isLogin = false;
	private User user = null;
	private List<LaporanHelper> listDataLaporan = new ArrayList<LaporanHelper>();
	private ArrayList<PrivateMessage> listPrivateMessages = new ArrayList<PrivateMessage>();
	
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

	public List<LaporanHelper> getListDataLaporan() {
		return listDataLaporan;
	}

	public void setListDataLaporan(List<LaporanHelper> listDataLaporan) {
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

	public List<PrivateMessage> getListPrivateMessages() {
		return listPrivateMessages;
	}

	public void setListPrivateMessages(ArrayList<PrivateMessage> listPrivateMessages) {
		this.listPrivateMessages = listPrivateMessages;
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
			FileHandler.saveDataToFile(context, LIST_PM, MODE, listPrivateMessages);
//			FileHandler.saveDataToFile(context, LIST_LAPORAN, Context.MODE_PRIVATE, (ArrayList<Laporan>) listDataLaporan);
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
	}
	
	public void loadFromFile(Context context){
		isLogin = FileHandler.getBooleanValue(context, IS_LOGIN);
		//serverAddress = FileHandler.getStringValue(context, SERVER_ADDRESS);
		authKey = FileHandler.getStringValue(context, AUTH_KEY);
		try {
			user = (User) FileHandler.loadDataFromFile(context, USER_DATA);
//			listDataLaporan = (ArrayList<Laporan>) FileHandler.loadDataFromFile(context, LIST_LAPORAN);
			listPrivateMessages = (ArrayList<PrivateMessage>) FileHandler.loadDataFromFile(context, LIST_PM);
			
		} catch (StreamCorruptedException e) {
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			
		}
		catch(NullPointerException ex){
			
		}
	}

}
