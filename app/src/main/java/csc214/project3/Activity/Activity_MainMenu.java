package csc214.project3.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import csc214.project3.Model.City;
import csc214.project3.Model.Collection;
import csc214.project3.R;
import csc214.project3.View.*;

public class Activity_MainMenu extends AppCompatActivity implements Fragment_RecView_MainMenu.clickListener{

    public static final String TAG = "My Tag";
    public static String userName;
    public static String Key_UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        getSupportActionBar().setTitle("Preview of Cities");
        Intent intent = getIntent();
        Log.d(TAG,""+intent.getExtras().getString(Key_UserName));
        userName = intent.getExtras().getString(Key_UserName); // get user name from intent
        Log.d(TAG, "onCreate() is called in Main Menu Activity class");
        Log.d(TAG, "In Main Menu Activity, current user is " + userName);

        FragmentManager fManager = getSupportFragmentManager();
        Fragment_RecView_MainMenu mFragment = new Fragment_RecView_MainMenu();

        Bundle data = new Bundle();
        data.putString(Activity_Login.Key_UserName, userName);
        mFragment.setArguments(data);

        fManager.beginTransaction()
                .add(R.id.activity_main_menu, mFragment) // add fragment (RecyclerView) to layout
                .commit();
        createToolbar();

    }

    public void onClicked(String cityName) {

        if(cityName ==null){
            Log.d(TAG, "NULL");
        }
        Intent intent = new Intent(Activity_MainMenu.this, Activity_CityDetail.class);
        intent.putExtra(Adapter_Cities.Key_CityName, cityName);
        startActivity(intent);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_type1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        boolean isSelected;

        if(item.getItemId() == R.id.menu_CreateProfile){
            Intent intent = new Intent(Activity_MainMenu.this, Activity_CreateProfile.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);
            isSelected = true;
        }

        else if(item.getItemId() == R.id.menu_LogOut){


            Intent intent = new Intent(Activity_MainMenu.this, Activity_Login.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d(TAG, "Menu Log out is clicked");
            isSelected = true;
        }
        else{ // default false
            isSelected = super.onOptionsItemSelected(item);
        }

        return isSelected;
    }

    private void createToolbar(){
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar_bottom);

        Button toProfile = (Button)findViewById(R.id.toProfile);
        toProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_MainMenu.this, Activity_CreateProfile.class);
                intent.putExtra(Activity_Login.Key_UserName, userName);
                startActivity(intent);
            }
        });
        mToolbar.setBackgroundResource(R.color.common_action_bar_splitter);
//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.menu_CreateProfile:
//                        Intent intent = new Intent(Activity_MainMenu.this, Activity_CreateProfile.class);
//                        intent.putExtra(Activity_Login.Key_UserName, userName);
//                        startActivity(intent);
//
//                        break;
//                }
//                return true;
//            }
//        });
//        mToolbar.inflateMenu(R.menu.menu_type1);
    }
}
