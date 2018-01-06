package csc214.project3.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import csc214.project3.Model.Collection;
import csc214.project3.R;

public class Activity_CityPlate extends AppCompatActivity{ // display the traditional plates for various cities

    private static String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plates);

        final String cityName = getIntent().getExtras().getString(Activity_MainMenu.Key_CityName);
        userName = getIntent().getExtras().getString(Activity_Login.Key_UserName);

        ImageView image = (ImageView)findViewById(R.id.image_plate01);
        TextView info = (TextView)findViewById(R.id.text_plate01);
        TextView plateName = (TextView)findViewById(R.id.plateName);

        if(cityName.equals("Dalian")){
            image.setImageResource(R.drawable.guobao_meat);
            info.setText(R.string.guobaoMeat_Info);
            plateName.setText(R.string.GuoBaoRou);
        }
        else if(cityName.equals("Xiamen")){
            image.setImageResource(R.drawable.zongzi);
            info.setText(R.string.ZongZi_Info);
            plateName.setText(R.string.ZongZi);
        }
        else if(cityName.equals("Guangzhou")){
            image.setImageResource(R.drawable.zaocha);
            info.setText(R.string.zaocha_info);
            plateName.setText(R.string.zaocha);
        }

        getSupportActionBar().setTitle(cityName);

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

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_type1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        boolean isSelected;

        if(item.getItemId() == R.id.menu_CreateProfile){
            Intent intent = new Intent(Activity_CityPlate.this, Activity_CreateProfile.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);
            isSelected = true;
        }

        else if(item.getItemId() == R.id.menu_MainMenu){
            Intent intent = new Intent(Activity_CityPlate.this, Activity_MainMenu.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d("My tag", "Menu gotoMainMenu is clicked");
            isSelected = true;
        }

        else if(item.getItemId() == R.id.menu_LogOut){


            Intent intent = new Intent(Activity_CityPlate.this, Activity_Login.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d("My tag", "Menu Log out is clicked");
            isSelected = true;
        }
        else if(item.getItemId() == R.id.menu_ShowFavorite){
            Intent intent = new Intent(Activity_CityPlate.this, Activity_Favorite.class);
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
