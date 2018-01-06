package csc214.project3.View;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import csc214.project3.Activity.Activity_Login;
import csc214.project3.Model.City;
import csc214.project3.Model.Collection;
import csc214.project3.R;

public class Fragment_RecView_Favorite extends Fragment{

    public interface clickListener{
        void onClicked(String cityName);
    }

    private Fragment_RecView_Favorite.clickListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (Fragment_RecView_Favorite.clickListener)context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (Fragment_RecView_Favorite.clickListener)activity;
    }

    public Fragment_RecView_Favorite(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("My Tag", "onCreateView is called in Favorite");
        View view = inflater.inflate(R.layout.fragment_favorite,container,false); // layout name
        RecyclerView Rview = (RecyclerView)view.findViewById(R.id.RecyclerView_Favorite); // rec view name in layout
        LinearLayoutManager LManager = new LinearLayoutManager(getActivity());
        Rview.setLayoutManager(LManager);

        String userName = getArguments().getString(Activity_Login.Key_UserName);
        ArrayList<City> favList = Collection.getFavoriteCity(userName);
        Rview.setAdapter(new Adapter_Favorite(favList, mListener));

        return view;
    }
}
