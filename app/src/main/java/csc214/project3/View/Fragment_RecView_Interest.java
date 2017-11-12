package csc214.project3.View;


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

import csc214.project3.Activity.Activity_CityInterest;
import csc214.project3.Controller.AudioManagement;
import csc214.project3.Model.Attraction;
import csc214.project3.Model.Audio;
import csc214.project3.Model.Collection;
import csc214.project3.R;

public class Fragment_RecView_Interest extends Fragment{

    private AudioManagement mAudioManagment;

    public Fragment_RecView_Interest(){
        //
    }

    public interface ClickListener{
        public void onClicked(Audio mAudio, AudioManagement mAudioManager, Context context);
    }

    private ClickListener mListener;

    public void onAttach(Context context){
        super.onAttach(context);
        mListener = (ClickListener)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("My Tag", "Interest is created");
        Log.d("My Tag", "Fragment is retained when rotated");
        setRetainInstance(true);// retain the fragment
        mAudioManagment = new AudioManagement(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mAudioManagment = new AudioManagement(getContext());
        Bundle data = getArguments();
        String city = data.getString(Activity_CityInterest.Key_CityName);

        Log.d("My Tag", "The attractions are from " + city);

        View view = inflater.inflate(R.layout.fragment_interest, container,false);
        RecyclerView Rview = (RecyclerView)view.findViewById(R.id.RecyclerView_Interest);

        LinearLayoutManager LManager = new LinearLayoutManager(getActivity());
        Rview.setLayoutManager(LManager);

        SQLiteDatabase myDatabase = Collection.getDatabase(getContext()); // get database
        ArrayList<Attraction> attList = ViewController.getCollection_Attraction(myDatabase, city);

        Rview.setAdapter(new Adapter_Attractions(attList, mAudioManagment, mListener));

        return view;
    }
}
