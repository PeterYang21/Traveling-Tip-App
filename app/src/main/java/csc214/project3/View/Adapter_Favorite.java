package csc214.project3.View;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import csc214.project3.Model.City;
import csc214.project3.R;

public class Adapter_Favorite extends RecyclerView.Adapter<Adapter_Favorite.ViewHolder_Fav>{
    private static String TAG = "My Tag";
    private ArrayList<City> favList;
    public Fragment_RecView_Favorite.clickListener mListener;

    public Adapter_Favorite(ArrayList<City> favList, Fragment_RecView_Favorite.clickListener listener){
        this.favList = favList;
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return favList.size();
    }

    @Override
    public ViewHolder_Fav onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext()); // get inflater from context
        View view = inflater.inflate(R.layout.viewholder_favorite, parent, false);

        return new ViewHolder_Fav(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder_Fav mHolder, int position) {
        City mCity = favList.get(position);
        Log.d(TAG, "In RecyclerView Favorite Adapter the position is " + position);
        Log.d(TAG, "The fav city is " + mCity.getCityName());

        mHolder.bindItemtoHolder(mCity);
    }

    public class ViewHolder_Fav extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageButton imageButton;

        private ViewHolder_Fav(final View itemView) {
            super(itemView);

            textView = (TextView)itemView.findViewById(R.id.Fav_CityName);
            imageButton = (ImageButton)itemView.findViewById(R.id.Fav_ImageButton);
        }

        private void bindItemtoHolder(final City mCity){
            Log.d(TAG, "In adapter favorite, img is"+mCity.getCityImageResource());
            imageButton.setImageResource(mCity.getCityImageResource());
            textView.setGravity(Gravity.CENTER);
            textView.setText(mCity.getCityName());

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
        }
    }

}

