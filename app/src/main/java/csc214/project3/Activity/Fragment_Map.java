package csc214.project3.Activity;


import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Map extends SupportMapFragment{

    private GoogleMap Map;
    public Fragment_Map(){
        //
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final double Latti = getArguments().getDouble(Activity_CityPreview.Key_Latti);
        final double Longi = getArguments().getDouble(Activity_CityPreview.Key_Longi);

        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Map = googleMap;
                showLocation(Latti, Longi);
            }
        });
    }

    public void showLocation(double latti, double longi){
        if(Map!=null){
//            Log.d("My Tag", "Location is shown");
            LatLng location = new LatLng(latti, longi);
            MarkerOptions locationMarker =new MarkerOptions().position(location);
            Map.clear();
            Map.addMarker(locationMarker);
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(location, 5.0f);

            Map.animateCamera(update);
        }
    }
}
