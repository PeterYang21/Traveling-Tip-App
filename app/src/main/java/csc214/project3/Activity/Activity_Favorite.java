package csc214.project3.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;
import csc214.project3.R;
import csc214.project3.View.Fragment_RecView_Favorite;

public class Activity_Favorite extends AppCompatActivity implements Fragment_RecView_Favorite.clickListener{
    public static final String TAG = "My Tag";
    public static String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().setTitle("My Favorite");

        Intent intent = getIntent();
        userName = intent.getExtras().getString(Activity_Login.Key_UserName); ///
        Log.d(TAG, "In Favorite Activity, current User Name is " + userName);

        FragmentManager fManager = getSupportFragmentManager();
        Fragment_RecView_Favorite mFragment = new Fragment_RecView_Favorite();

        Bundle data = new Bundle();
        data.putString(Activity_Login.Key_UserName, userName);
        mFragment.setArguments(data);

        fManager.beginTransaction()
                .add(R.id.activity_favorite, mFragment) // add fragment (RecyclerView) to layout
                .commit();
    }

    public void onClicked(String cityName) {

        if(cityName ==null){
            Log.d(TAG, "NULL");
        }
        Intent intent = new Intent(Activity_Favorite.this, Activity_CityPreview.class);
        Bundle extras = new Bundle();
        extras.putString(Activity_Login.Key_UserName, userName);
        Log.d(TAG, "THE KEY:" + Activity_MainMenu.Key_CityName+" " + Activity_Login.Key_UserName);
        extras.putString(Activity_MainMenu.Key_CityName, cityName);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_type1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        boolean isSelected;

        if(item.getItemId() == R.id.menu_CreateProfile){
            Intent intent = new Intent(Activity_Favorite.this, Activity_CreateProfile.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);
            isSelected = true;
        }
        else if(item.getItemId() == R.id.menu_MainMenu){
            Intent intent = new Intent(Activity_Favorite.this, Activity_MainMenu.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d("My tag", "Menu gotoMainMenu is clicked");
            isSelected = true;
        }
        else if(item.getItemId() == R.id.menu_LogOut){

            Intent intent = new Intent(Activity_Favorite.this, Activity_Login.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d(TAG, "Menu Log out is clicked");
            isSelected = true;
        }
        else if(item.getItemId() == R.id.menu_ShowFavorite){
            Toast.makeText(getApplicationContext(), "You are now in My Favorite Page", Toast.LENGTH_LONG).show();
            Log.d("My tag", "Menu gotoFavorite is clicked");
            isSelected = true;
        }

        else{ // default false
            isSelected = super.onOptionsItemSelected(item);
        }

        return isSelected;
    }
}
