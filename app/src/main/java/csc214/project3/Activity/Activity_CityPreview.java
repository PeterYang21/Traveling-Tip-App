package csc214.project3.Activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import csc214.project3.Model.Collection;
import csc214.project3.R;

import android.widget.TextView;

public class Activity_CityPreview extends AppCompatActivity{

    public static String Key_Latti = "Lattidude";
    public static String Key_Longi = "Longitude";
    public static String userName;
    public static String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_city_detail);

        userName = getIntent().getExtras().getString(Activity_Login.Key_UserName);
        Log.d("My Tag","Activity CityDetail User: "+userName);

        cityName = getIntent().getExtras().getString(Activity_MainMenu.Key_CityName); // get current City from prev intent
        //getSupportActionBar().setTitle(cityName);
        Log.d("My Tag","Activity CityDetail City: "+cityName);

        LayoutInflater mInflator = LayoutInflater.from(this);
        View custom_actionbar = mInflator.inflate(R.layout.view_custom_actionbar, null);

        // set up action bar
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        ActionBar actionBar = getSupportActionBar();

        ImageButton Button_Places = (ImageButton)findViewById(R.id.Button_PlaceofInterests);
        ImageButton Button_Plates = (ImageButton)findViewById(R.id.Button_CityPlates);
        Button Button_showMap = (Button)findViewById(R.id.button_showMap);

        if(Button_Places==null){
            Log.d("MY tag", "VIEW IS NULL");
        }

        if(cityName.equals("Dalian")) {
            // pass related parameters to generic function for information processing
            // and set up action bar params
            processing(actionBar, custom_actionbar, params,
                        Button_Places, Button_Plates, Button_showMap,
                        R.drawable.dalian_interest, R.drawable.guobao_meat, 38.9140, 121.6147);
        }
        else if(cityName.equals("Xiamen")) {
            processing(actionBar, custom_actionbar, params,
                    Button_Places, Button_Plates, Button_showMap,
                    R.drawable.xiamen_interest, R.drawable.xiamen_plate_preview, 24.4798, 118.0894);
        }
        else if(cityName.equals("Guangzhou")){
            processing(actionBar, custom_actionbar, params,
                    Button_Places, Button_Plates, Button_showMap,
                    R.drawable.guangzhou_interest_preview, R.drawable.guangdong_plate_preview, 23.1291, 113.2644);
        }

    }

    public void onDestroy(){
        super.onDestroy();
        Log.d("My Tag", "On destroyed is called");
    }

    public void processing(ActionBar actionBar, View custom_actionbar, ActionBar.LayoutParams params,
                           ImageButton Button_Places, ImageButton Button_Plates, Button Button_showMap,
                           int image_interest, int image_plate_preview, final double latti, final double longi){
        actionBar.setCustomView(custom_actionbar, params);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);

        final ImageButton fav_button = (ImageButton)findViewById(R.id.custom_actionbar_image);
        // process favorite button issues
        Collection.doFavButton(fav_button, userName, cityName);

        Log.d("MY Tag","DETAILED CITY IS "+decodeImage(image_interest));
        Button_Places.setImageBitmap(decodeImage(image_interest));
        Button_Places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_CityPreview.this, Activity_CityInterest.class);
                intent.putExtra(Activity_MainMenu.Key_CityName, cityName);
                intent.putExtra(Activity_Login.Key_UserName, userName);
                startActivity(intent);
            }
        });

        Button_showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_CityPreview.this, Activity_Map.class);
                intent.putExtra(Key_Latti, latti); // send location to map API
                intent.putExtra(Key_Longi, longi);
                intent.putExtra(Activity_MainMenu.Key_CityName, cityName);
                startActivity(intent);
            }
        });

        Button_Plates.setImageBitmap(decodeImage(image_plate_preview));
        Button_Plates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_CityPreview.this, Activity_CityPlate.class);
                intent.putExtra(Activity_MainMenu.Key_CityName, cityName);
                intent.putExtra(Activity_Login.Key_UserName, userName);
                startActivity(intent);
            }
        });

        TextView actionBarText = (TextView)findViewById(R.id.custom_actionbar_text);
        actionBarText.setText(cityName);
    }


    public Bitmap decodeImage(int resourceId) {

        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resourceId, option);

        int SIZE = 300; // scale to the new size
        int scale = 1;
        while ((option.outWidth / scale / 2 >= SIZE) && (option.outHeight / scale / 2 >= SIZE)){
            scale *= 2; // get the correct scale
        }
        BitmapFactory.Options newOption = new BitmapFactory.Options();
        newOption.inSampleSize = scale;
        return BitmapFactory.decodeResource(getResources(), resourceId, newOption);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_type1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        boolean isSelected;

        if(item.getItemId() == R.id.menu_CreateProfile){
            Intent intent = new Intent(Activity_CityPreview.this, Activity_CreateProfile.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);
            isSelected = true;
        }

        else if(item.getItemId() == R.id.menu_LogOut){

            Intent intent = new Intent(Activity_CityPreview.this, Activity_Login.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d("My tag", "Menu Log out is clicked");
            isSelected = true;
        }
        else if(item.getItemId() == R.id.menu_MainMenu){
            Intent intent = new Intent(Activity_CityPreview.this, Activity_MainMenu.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d("My tag", "Menu gotoMainMenu is clicked");
            isSelected = true;
        }
        else if(item.getItemId() == R.id.menu_ShowFavorite){
            Intent intent = new Intent(Activity_CityPreview.this, Activity_Favorite.class);
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


