package app.positiveculture.com.agent.screen.locationaddress.map;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by BB on 8/22/2017.
 */

public class MapManager {

  private GoogleMap googleMap;
  private Context mContext;

  public MapManager(Context context, GoogleMap googleMap) {
    this.mContext = context;
    this.googleMap = googleMap;
    initMap();

//		LatLng latLng = new LatLng(21.036957,105.834711);
//		String title = "Lang Bac";
//		addMarker(latLng,title);
  }

  public GoogleMap getGoogleMap() {
    return googleMap;
  }

  public void addMarker(LatLng latLng, String title) {
    MarkerOptions markerOptions = new MarkerOptions();
    markerOptions.draggable(true);
    markerOptions.position(latLng);
    markerOptions.title(title);
    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
    googleMap.clear();
    googleMap.addMarker(markerOptions);
    CameraUpdate cameraUpdateFactory = CameraUpdateFactory.newLatLngZoom(latLng, 16);
    googleMap.animateCamera(cameraUpdateFactory);
  }

  private void initMap() {
    googleMap.getUiSettings().setAllGesturesEnabled(true);
    googleMap.getUiSettings().setZoomControlsEnabled(false);
    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    googleMap.getUiSettings().setMapToolbarEnabled(true);
    if (ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      return;
    }
  }

  private void turnOnGPS() {
    LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    boolean isGPSenable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    if (isGPSenable == false) {
      Intent intent = new Intent();
      intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
      mContext.startActivity(intent);
    }
  }
}
