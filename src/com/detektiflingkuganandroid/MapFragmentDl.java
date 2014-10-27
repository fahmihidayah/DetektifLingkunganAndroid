package com.detektiflingkuganandroid;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapFragmentDl extends Fragment {
	LatLng posisi;
	MapView mapView;
	GoogleMap map;
 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.map_fragment, container, false);
 
 		// Gets the MapView from the XML layout and creates it
		mapView = (MapView) v.findViewById(R.id.mapView);
		mapView.onCreate(savedInstanceState);
		double longitude = getActivity().getIntent().getDoubleExtra("long", 0);
		double latitude = getActivity().getIntent().getDoubleExtra("lat", 0);
		posisi = new LatLng(latitude, longitude);
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(posisi);
		
		// Gets to GoogleMap from the MapView and does initialization stuff
		map = mapView.getMap();
		//map.getUiSettings().setMyLocationButtonEnabled(true);
		//map.setMyLocationEnabled(true);
 
		MapsInitializer.initialize(this.getActivity());
		map.addMarker(markerOptions);
		// Updates the location and zoom of the MapView
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(posisi, 10);
		map.animateCamera(cameraUpdate);
 
		return v;
	}
 
	@Override
	public void onResume() {
		mapView.onResume();
		super.onResume();
	}
 
	@Override
	public void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}
 
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mapView.onLowMemory();
	}
}
