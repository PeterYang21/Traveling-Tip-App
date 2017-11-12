package csc214.project3.View;


import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import csc214.project3.Model.City;
import csc214.project3.Model.Collection;
import csc214.project3.R;

public class Fragment_RecView_MainMenu extends Fragment{

    public interface clickListener{
        public void onClicked(String cityName);
    }

    private clickListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (clickListener)context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (clickListener)activity;
    }

    public Fragment_RecView_MainMenu(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) { // create recycler view from adapter
        Log.d("My Tag", "onCreateView is called");
        View view = inflater.inflate(R.layout.fragment_main_menu,container,false);
        RecyclerView Rview = (RecyclerView)view.findViewById(R.id.RecyclerView_MainMenu);

        LinearLayoutManager LManager = new LinearLayoutManager(getActivity());
        Rview.setLayoutManager(LManager);

        SQLiteDatabase myDatabase = Collection.getDatabase(getContext()); // get database
        ArrayList<City> cityList = ViewController.getCollection_City(myDatabase);

        Rview.setAdapter(new Adapter_Cities(cityList, myDatabase, mListener));

        return view;
    }
}
