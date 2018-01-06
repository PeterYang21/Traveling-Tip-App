package csc214.project3.View;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import csc214.project3.Controller.AudioManagement;
import csc214.project3.Model.Attraction;
import csc214.project3.Model.Audio;
import csc214.project3.R;

public class Adapter_Attractions extends RecyclerView.Adapter<Adapter_Attractions.ViewHolder_Attractions>{

    private ArrayList<Attraction> attList;
    private AudioManagement audioManager;
    private Fragment_RecView_Interest.ClickListener mListener;
    private ArrayList<Audio> aList;

    public Adapter_Attractions(ArrayList<Attraction> attList, AudioManagement audioManager, Fragment_RecView_Interest.ClickListener mListener){
        this.attList = attList;
        this.audioManager = audioManager;
        this.mListener = mListener;

        this.aList = audioManager.getAudioList();
    }

    @Override
    public int getItemCount() {
        return attList.size();
    }

    @Override
    public ViewHolder_Attractions onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.viewholder_attraction, parent, false);

        ViewHolder_Attractions mHolder = new ViewHolder_Attractions(view);
        return mHolder;
    }

        @Override
        public void onBindViewHolder (ViewHolder_Attractions holder,int position){
            Attraction att = attList.get(position);
            holder.bindItemtoHolder(att);
        }

        public class ViewHolder_Attractions extends RecyclerView.ViewHolder {

            private TextView name;
            private TextView info;
            private ImageView preview;
            private ImageButton image01;
            private ImageView image02;
            private ImageView image03;

            private String attractionName;

//            private Audio mAudio = aList.get(0);

            private ViewHolder_Attractions(final View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.att_name);
                info = (TextView) itemView.findViewById(R.id.att_info);
                preview = (ImageView) itemView.findViewById(R.id.att_preview);
                image01 = (ImageButton) itemView.findViewById(R.id.att_image01);

                Log.d("MY Tag Size", ""+aList.size());

                image01.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("My Tag", "IS PLAYED " + attractionName);
                        if(attractionName.equals("XingHai Square")){

                            Audio mAudio = aList.get(0);
                            Context context = audioManager.get_Context();
                            Log.d("My Tag", "IS PLAYED");
                            mListener.onClicked(mAudio, audioManager, context);
                        }
                    }
                });

                image02 = (ImageView) itemView.findViewById(R.id.att_image02);
                image03 = (ImageView) itemView.findViewById(R.id.att_image03);
            }

            private void bindItemtoHolder(final Attraction att) {
                name.setText(att.getName());
                this.attractionName = att.getName();

                info.setTextSize(18);
                info.setText(att.getInfo());
//                info.setTextSize(12);
                preview.setImageResource(att.getPreviewImage());
//                Log.d("My Tag", ""+att.getImage01());
                image01.setImageResource(att.getImage01());
                image02.setImageResource(att.getImage02());
                image03.setImageResource(att.getImage03());
            }
        }
    }
