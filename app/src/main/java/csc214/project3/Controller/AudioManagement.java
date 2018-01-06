package csc214.project3.Controller;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import csc214.project3.Model.Audio;


public class AudioManagement {

    private AssetManager mAssetManager;
    private ArrayList<Audio> mAudioList;
    private SoundPool mAudioPool;
    private String AUDIOFOLDER = "SoundTracks";
    private Context mContext;

    public AudioManagement(Context context){
        mContext = context;
        mAssetManager = context.getAssets();
        mAudioList = new ArrayList<>();
        mAudioPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);

        String[] audioCollection;
        try {
            audioCollection = mAssetManager.list(AUDIOFOLDER); // get the collection of audios from the target folder

            for(String audioName : audioCollection){
                String path = AUDIOFOLDER + "/" + audioName;
                Log.d("My Tag", "The Audio path is " + path);
                Audio newAudio = new Audio(audioName);
                mAudioList.add(newAudio); // add audio to the list //

                AssetFileDescriptor AFDescriptor = mAssetManager.openFd(path);
                int audioID = mAudioPool.load(AFDescriptor, 1); // get integer ID from pool
                newAudio.setID(audioID);
                Log.d("My Tag", "The audioID is " + audioID);
            }
        }
        catch (IOException IOE){
            Log.d("My Tag", "Cannot load audio from collection", IOE);
        }
    }

    public void playAudio(Audio audio){
        int audioID = audio.getID();
        if(audioID != 0){
            mAudioPool.play(
                    audioID, // audio ID
                    1.0f, // left volume
                    1.0f, // right volume
                    1, // priority
                    0, // no loop
                    1.0f // play at normal speed
            );
        }
    }

    public Context get_Context(){
        return mContext;
    }

    public ArrayList<Audio> getAudioList(){
        return mAudioList;
    }
    public void release(){ // release the pool if necessary
        mAudioPool.release();
    }
}
