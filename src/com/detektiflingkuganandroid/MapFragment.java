package com.detektiflingkuganandroid;

import java.util.ArrayList;
import java.util.List;

import com.engine.FollowerKuEngine;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.models.DataSingleton;
import com.models.Laporan;
import com.models.LaporanHelper;
import com.models.User;
import com.models.UserHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class MapFragment extends Fragment {

	LatLng posisi;
	MapView mapView;
	GoogleMap map;

	private void initialComponent() {
		List<LaporanHelper> listLaporan = DataSingleton.getInstance()
				.getListDataLaporan();
		for (Laporan laporan : listLaporan) {
			MarkerOptions mo = new MarkerOptions();
			mo.position(new LatLng(laporan.getLatitude(), laporan
					.getLongitude()));
			mo.title(laporan.getDataLaporan());
			mo.snippet("" + laporan.getLongitude() + ", "
					+ laporan.getLatitude());
			map.addMarker(mo);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.map_fragment, container, false);

		// Gets the MapView from the XML layout and creates it
		mapView = (MapView) v.findViewById(R.id.mapView);
		mapView.onCreate(savedInstanceState);
		// MarkerOptions markerOptions = new MarkerOptions();
		// markerOptions.position(posisi);

		// Gets to GoogleMap from the MapView and does initialization stuff
		map = mapView.getMap();
		map.getUiSettings().setMyLocationButtonEnabled(true);
		map.setMyLocationEnabled(true);
		// LatLng latLng = new LatLng(map.getMyLocation().getLatitude(),
		// map.getMyLocation().getLongitude());
		// MapsInitializer.initialize(this.getActivity());
		// map.addMarker(markerOptions);
		// Updates the location and zoom of the MapView
		// CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,
		// 10);
		//
		// map.animateCamera(cameraUpdate);
		initialComponent();
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
