package csc214.project3.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import csc214.project3.Controller.AudioManagement;
import csc214.project3.Model.Audio;
import csc214.project3.Model.Collection;
import csc214.project3.R;
import csc214.project3.View.Fragment_RecView_Interest;

public class Activity_CityInterest extends AppCompatActivity implements Fragment_RecView_Interest.ClickListener{
    public static final String TAG = "My Tag";
    public static String cityName;
    private static String userName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.host_attraction);

        cityName = getIntent().getExtras().getString(Activity_MainMenu.Key_CityName);
        userName = getIntent().getExtras().getString(Activity_Login.Key_UserName);

        getSupportActionBar().setTitle(cityName);

        Log.d(TAG, "In Activity_CityInterest the city is " + cityName);

        FragmentManager fManager = getSupportFragmentManager();
        Fragment_RecView_Interest mFragment = new Fragment_RecView_Interest(); // get recycler view for the attractions
//        Toast.makeText(getApplicationContext(), "Try clicking one of the images to play its audio.", Toast.LENGTH_LONG).show();
        Bundle data = new Bundle();
        data.putString(Activity_MainMenu.Key_CityName, cityName);
        mFragment.setArguments(data);

        fManager.beginTransaction()
                .add(R.id.hostAttraction, mFragment)
                .commit();

        /*
         * implementation if allocating more memory
         */
        // set up action bar
//        LayoutInflater mInflator = LayoutInflater.from(this);
//        View custom_actionbar = mInflator.inflate(R.layout.view_custom_actionbar, null);
//
//        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setCustomView(custom_actionbar, params);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowCustomEnabled(true);
//
//        final ImageButton fav_button = (ImageButton)findViewById(R.id.custom_actionbar_image);
//        // process favorite button issues
//        Collection.doFavButton(fav_button, userName, cityName);
    }

    public void onClicked(Audio mAudio, AudioManagement mAudioManager, Context context){
//        Toast.makeText(context, "Now playing " + mAudio.getName(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(context, "Now playing audio for you", Toast.LENGTH_SHORT).show();
        mAudioManager.playAudio(mAudio);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_type1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        boolean isSelected;

        if(item.getItemId() == R.id.menu_CreateProfile){
            Intent intent = new Intent(Activity_CityInterest.this, Activity_CreateProfile.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);
            isSelected = true;
        }

        else if(item.getItemId() == R.id.menu_MainMenu){
            Intent intent = new Intent(Activity_CityInterest.this, Activity_MainMenu.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d("My tag", "Menu gotoMainMenu is clicked");
            isSelected = true;
        }

        else if(item.getItemId() == R.id.menu_LogOut){


            Intent intent = new Intent(Activity_CityInterest.this, Activity_Login.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d("My tag", "Menu Log out is clicked");
            isSelected = true;
        }
        else if(item.getItemId() == R.id.menu_ShowFavorite){
            Intent intent = new Intent(Activity_CityInterest.this, Activity_Favorite.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d("My tag", "Menu gotoFavorite is clicked");
            isSelected = true;
        }

        else{ // default false
            isSelected = super.onOptionsItemSelected(item);
        }

        return isSelected;
    }
}
