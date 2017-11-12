package csc214.project3.Activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import csc214.project3.Model.City;
import csc214.project3.R;
import csc214.project3.View.Adapter_Cities;

public class Fragment_CityDetail extends Fragment{

    public static String Key_CityName = "CityName";

    public Fragment_CityDetail(){
        //
    }

    public static Fragment_CityDetail newInstance(String cityName){
        Fragment_CityDetail detail = new Fragment_CityDetail();
        Bundle args = new Bundle();
        args.putString(Key_CityName, cityName);
        Log.d("My Tag", "NEW Instance: " + cityName);
        detail.setArguments(args);

        return detail;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("My Tag", "onActivityCrreated");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
//        Log.d("My Tag", "ONCRATEVIEW IS CALLED FOR DETAIL FRAGMENT");
        View v = inflater.inflate(R.layout.fragment_city_detail, container, false);
        ImageButton interestPreview = (ImageButton)v.findViewById(R.id.Button_PlaceofInterests);
        String cityName = getArguments().getString(Adapter_Cities.Key_CityName);
        Log.d("My Tag", "The city in DETAIL FRAGMENT "+cityName);

        if(cityName==("Dalian")){
            Log.d("My Tag", "PREVIEW IS SET FOR DETAIL FRAGMENT");
            interestPreview.setImageResource(R.drawable.dalian_interest);
        }
        return v;
    }
}
