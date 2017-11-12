package csc214.project3.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import csc214.project3.R;
import csc214.project3.View.Adapter_Cities;

public class Activity_CityPlate extends AppCompatActivity{ // display the traditional plates for various cities


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plates);

        String cityName = getIntent().getExtras().getString(Adapter_Cities.Key_CityName);

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
    }

}
