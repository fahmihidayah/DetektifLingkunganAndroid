package com.models;

import java.util.Observable;

public class DataSingleton extends Observable {
	private static DataSingleton instance;
	
	private String serverAddress="192.168.1.8:9000";
	
	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	protected DataSingleton() {

	}

	public static DataSingleton getInstance() {
		if (instance == null) {
			instance = new DataSingleton();
		}
		return instance;
	}

	public void notifyObserverDataChange(){
		setChanged();
		notifyObservers();
	}

}
