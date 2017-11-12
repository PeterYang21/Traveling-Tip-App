package csc214.project3.Activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import csc214.project3.R;

public class Activity_Map extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_attraction);

        Fragment_Map fragment = new Fragment_Map();
        double Latti = getIntent().getDoubleExtra(Activity_CityDetail.Key_Latti,0.00); // get location for map API
        double Longi = getIntent().getDoubleExtra(Activity_CityDetail.Key_Longi,0.00);

        Bundle args = new Bundle();
        args.putDouble(Activity_CityDetail.Key_Latti, Latti);
        args.putDouble(Activity_CityDetail.Key_Longi, Longi);
        fragment.setArguments(args);

        FragmentManager fManager = getSupportFragmentManager();

        fManager.beginTransaction()
                .add(R.id.hostAttraction, fragment)
                .commit();
    }
}
