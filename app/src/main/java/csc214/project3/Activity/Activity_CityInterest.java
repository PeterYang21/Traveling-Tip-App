package csc214.project3.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import csc214.project3.Controller.AudioManagement;
import csc214.project3.Model.Audio;
import csc214.project3.R;
import csc214.project3.View.Fragment_RecView_Interest;

public class Activity_CityInterest extends AppCompatActivity implements Fragment_RecView_Interest.ClickListener{
    public static final String TAG = "My Tag";
    public static String cityName;
    public static String Key_CityName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_attraction);

        Intent intent = getIntent();
        cityName = intent.getExtras().getString(Key_CityName);
        getSupportActionBar().setTitle(cityName);

        Log.d(TAG, "In Activity_CityInterest the city is " + cityName);

        FragmentManager fManager = getSupportFragmentManager();
        Fragment_RecView_Interest mFragment = new Fragment_RecView_Interest(); // get recycler view for the attractions
        Toast.makeText(getApplicationContext(), "Try clicking one of the images to play its audio.", Toast.LENGTH_LONG).show();
        Bundle data = new Bundle();
        data.putString(Key_CityName, cityName);
        mFragment.setArguments(data);

        fManager.beginTransaction()
                .add(R.id.hostAttraction, mFragment)
                .commit();
    }

    public void onClicked(Audio mAudio, AudioManagement mAudioManager, Context context){
//        Toast.makeText(context, "Now playing " + mAudio.getName(), Toast.LENGTH_SHORT).show();
        Toast.makeText(context, "Now playing audio for you", Toast.LENGTH_SHORT).show();
        mAudioManager.playAudio(mAudio);
    }
}
