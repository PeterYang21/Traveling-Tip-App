package csc214.project3.View;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import csc214.project3.Activity.*;
import csc214.project3.Database.DB_CursorWrapper;
import csc214.project3.Database.DB_Schema;

import csc214.project3.Model.City;
import csc214.project3.R;

public class Adapter_Cities extends RecyclerView.Adapter<Adapter_Cities.ViewHolder_Cities>{

    private static String TAG = "My Tag";
    private ArrayList<City> cityList;
    private SQLiteDatabase myDatabase;
    private Context mContext;
//    public static String Key_CityName = "Key_CityName";
    public Fragment_RecView_MainMenu.clickListener mListener;

    public Adapter_Cities(ArrayList<City> cityList, SQLiteDatabase db, Fragment_RecView_MainMenu.clickListener listener){
        this.cityList = cityList;
        this.myDatabase = db;
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    @Override
    public ViewHolder_Cities onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); // get inflater from context
        View view = inflater.inflate(R.layout.viewholder_main_menu, parent, false);

        ViewHolder_Cities mHolder = new ViewHolder_Cities(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder_Cities mHolder, int position) {
        City city = cityList.get(position);
        Log.d(TAG, "In RecyclerView Cities Adapter the position is " + position);
        Log.d(TAG, "The city is " + city.getCityName());

        mHolder.bindItemtoHolder(city);
    }


    public class ViewHolder_Cities extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageButton imageButton;

        private ViewHolder_Cities(final View itemView){
            super(itemView);
            mContext = itemView.getContext();

            textView = (TextView)itemView.findViewById(R.id.MainMenu_CityName);
            imageButton = (ImageButton)itemView.findViewById(R.id.MainMenu_ImageButton);
        }

        private void bindItemtoHolder(final City city){

            String SQL_getCity = "Select * from " + DB_Schema.CityTable.TABLE_NAME + " Where "
                                    + DB_Schema.CityTable.Columns.CITY_IMAGE + " = ?";
            Cursor cursor = myDatabase.rawQuery(SQL_getCity, new String[]{String.valueOf(city.getCityImageResource())});

            DB_CursorWrapper mWrapper = new DB_CursorWrapper(cursor);
            mWrapper.moveToFirst();
            Log.d("My tag", "City Cursor's POS is " + mWrapper.getPosition());
            final City mCity = mWrapper.getCity();

            Log.d("My Tag", ""+city.getCityName());
            Log.d("My Tag", ""+mCity.getCityName());

            imageButton.setImageResource(mCity.getCityImageResource());
            textView.setText(city.getCityName());

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mCity.getCityName().equals("Dalian")){
                        mListener.onClicked(mCity.getCityName());
                        Log.d(TAG, "City Dalian is CLICKED");
                    }
                    else if(mCity.getCityName().equals("Xiamen")){
                        mListener.onClicked(mCity.getCityName());
                        Log.d(TAG, "City Xiamen is CLICKED");
                    }
                    else if(mCity.getCityName().equals("Guangzhou")){
                        mListener.onClicked(mCity.getCityName());
                        Log.d(TAG, "City Guangzhou is CLICKED");
                    }
                }});

            textView.setOnClickListener(new View.OnClickListener() { // show a prompt dialog while textview is clicked
                @Override
                public void onClick(View v) {
                    AppCompatActivity context = (AppCompatActivity)itemView.getContext();
                    FragmentManager fManager = context.getSupportFragmentManager();
                    Fragment_Dialog fragment_dialog = Fragment_Dialog.newInstance("Thanks for your interest in " + mCity.getCityName()
                            + "! Press its image to detect more about this city.");
                    fragment_dialog.show(fManager, "Prompt");
                }
            });
        }
    }
}
