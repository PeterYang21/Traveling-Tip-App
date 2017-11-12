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
import android.widget.ImageView;

import csc214.project3.R;
import csc214.project3.View.Adapter_Cities;

public class Activity_CityDetail extends AppCompatActivity{

    public static String Key_Latti = "Lattidude";
    public static String Key_Longi = "Longitude";
    public static String userName;
    public static String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_city_detail);

        userName = getIntent().getExtras().getString(Activity_MainMenu.Key_UserName);

        Intent intent = getIntent();
        cityName = intent.getExtras().getString(Adapter_Cities.Key_CityName);
        //getSupportActionBar().setTitle(cityName);

        // TODO: custom support bar
        LayoutInflater mInflator = LayoutInflater.from(this);
        View custom_actionbar = mInflator.inflate(R.layout.view_custom_actionbar, null);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(custom_actionbar, params);

        ImageButton Button_Places = (ImageButton)findViewById(R.id.Button_PlaceofInterests);
        ImageButton Button_Plates = (ImageButton)findViewById(R.id.Button_CityPlates);
        Button Button_showMap = (Button)findViewById(R.id.button_showMap);

        if(Button_Places==null){
            Log.d("MY tag", "VIEW IS NULL");
        }
//        Button_Places.setImageBitmap(decodeImage(R.drawable.dalian_interest));

        if(cityName.equals("Dalian")) {
            // pass related parameters to generic function for information processing
            processing(actionBar, custom_actionbar, params,
                        Button_Places, Button_Plates, Button_showMap,
                        R.drawable.dalian_interest, R.drawable.guobao_meat, 38.9140, 121.6147);
            //getSupportActionBar().setTitle("City of Dalian");
//            actionBar.setCustomView(custom_actionbar, params);
//            actionBar.setDisplayShowTitleEnabled(false);
//            actionBar.setDisplayShowCustomEnabled(true);
//
//            Log.d("MY Tag","DETAILED CITY IS "+decodeImage(R.drawable.dalian_interest));
//            Button_Places.setImageBitmap(decodeImage(R.drawable.dalian_interest));
//            Button_Places.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Activity_CityDetail.this, Activity_CityInterest.class);
//                    intent.putExtra(Adapter_Cities.Key_CityName, cityName);
//                    startActivity(intent);
//                }
//            });
//
//            Button_showMap.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Activity_CityDetail.this, Activity_Map.class);
//                    intent.putExtra(Key_Latti,38.9140); // send location to map API
//                    intent.putExtra(Key_Longi,121.6147);
//                    startActivity(intent);
//                }
//            });
//
//            Button_Plates.setImageBitmap(decodeImage(R.drawable.guobao_meat));
//            Button_Plates.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Activity_CityDetail.this, Activity_CityPlate.class);
//                    intent.putExtra(Adapter_Cities.Key_CityName, cityName);
//                    startActivity(intent);
//                }
//            });
        }
        else if(cityName.equals("Xiamen")) {
            processing(actionBar, custom_actionbar, params,
                    Button_Places, Button_Plates, Button_showMap,
                    R.drawable.xiamen_interest, R.drawable.xiamen_plate_preview, 24.4798, 118.0894);
//            getSupportActionBar().setTitle("City of Xiamen");
//            Log.d("MY Tag","DETAILED CITY IS "+decodeImage(R.drawable.xiamen_interest));
//            Button_Places.setImageBitmap(decodeImage(R.drawable.xiamen_interest));
//            Button_Places.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Activity_CityDetail.this, Activity_CityInterest.class);
//                    intent.putExtra(Adapter_Cities.Key_CityName, cityName);
//                    startActivity(intent);
//                }
//            });
//
//            Button_showMap.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Activity_CityDetail.this, Activity_Map.class);
//                    intent.putExtra(Key_Latti, 24.4798); // send location to map API
//                    intent.putExtra(Key_Longi, 118.0894);
//                    startActivity(intent);
//                }
//            });
//
//            Button_Plates.setImageBitmap(decodeImage(R.drawable.xiamen_plate_preview));
//            Button_Plates.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Activity_CityDetail.this, Activity_CityPlate.class);
//                    intent.putExtra(Adapter_Cities.Key_CityName, cityName);
//                    startActivity(intent);
//                }
//            });
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

        Log.d("MY Tag","DETAILED CITY IS "+decodeImage(image_interest));
        Button_Places.setImageBitmap(decodeImage(image_interest));
        Button_Places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_CityDetail.this, Activity_CityInterest.class);
                intent.putExtra(Adapter_Cities.Key_CityName, cityName);
                startActivity(intent);
            }
        });

        Button_showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_CityDetail.this, Activity_Map.class);
                intent.putExtra(Key_Latti, latti); // send location to map API
                intent.putExtra(Key_Longi, longi);
                startActivity(intent);
            }
        });

        Button_Plates.setImageBitmap(decodeImage(image_plate_preview));
        Button_Plates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_CityDetail.this, Activity_CityPlate.class);
                intent.putExtra(Adapter_Cities.Key_CityName, cityName);
                startActivity(intent);
            }
        });
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
            Intent intent = new Intent(Activity_CityDetail.this, Activity_CreateProfile.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);
            isSelected = true;
        }

        else if(item.getItemId() == R.id.menu_LogOut){

            Intent intent = new Intent(Activity_CityDetail.this, Activity_Login.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d("My tag", "Menu Log out is clicked");
            isSelected = true;
        }
        else if(item.getItemId() == R.id.menu_MainMenu){
            Intent intent = new Intent(Activity_CityDetail.this, Activity_MainMenu.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d("My tag", "Menu gotoMainMenu is clicked");
            isSelected = true;
        }
        else{ // default false
            isSelected = super.onOptionsItemSelected(item);
        }

        return isSelected;
    }
}


