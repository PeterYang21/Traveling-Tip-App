package csc214.project3.Activity;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v4.content.FileProvider;
import android.graphics.Bitmap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.UUID;

import csc214.project3.BuildConfig;
import csc214.project3.Controller.SetBitmap;
import csc214.project3.Model.Collection;
import csc214.project3.Model.Profile;
import csc214.project3.Model.User;
import csc214.project3.R;

public class Activity_CreateProfile extends AppCompatActivity {

    public static final String TAG = "My Tag";
    private static File mPicFile;
    private ImageView profile_Pic;
    private EditText Text_Name;
    private EditText Text_Home;
    private EditText Text_Bio;

    public static String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        Intent intent = getIntent();
        Log.d(TAG, "In Create Profile Activity, current intent:" + intent);
        userName = intent.getExtras().getString(Activity_Login.Key_UserName);
        Log.d(TAG, "In Create Profile Activity, current User Name is " + userName);

        Button Button_TakePic = (Button)findViewById(R.id.Button_TakePhoto); // click the button to take picture
        Button_TakePic.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                String picName = "IMG_" + UUID.randomUUID().toString() + ".jpg";
                File picDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES); // get pictures directory in external storage

                mPicFile = new File(picDirectory, picName);

                Uri picUri = FileProvider.getUriForFile(Activity_CreateProfile.this, // modified for >= API 23
                        BuildConfig.APPLICATION_ID + ".provider",
                        mPicFile);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);

                startActivityForResult(intent, 1);
            }
        });

        Text_Name = (EditText)findViewById(R.id.Text_EnterName);
        Text_Home = (EditText)findViewById(R.id.Text_EnterHometown);
        Text_Bio = (EditText)findViewById(R.id.Text_EnterBio);
        profile_Pic= (ImageView)findViewById(R.id.profile_Image);

        if(Collection.checkProfile(userName) != null){ // add content to profile UI if user's profile has already existed
            Profile origProfile = Collection.checkProfile(userName);
            Text_Name.setText(origProfile.getFullName());
            Text_Home.setText(origProfile.getHomeTown());
            Text_Bio.setText(origProfile.getBio());

            Bitmap bitmap_Pic = SetBitmap.getBitmap(origProfile.getPathToPhoto(), profile_Pic.getWidth(),profile_Pic.getHeight());
            profile_Pic.setImageBitmap(bitmap_Pic);

            getSupportActionBar().setTitle("Update Your Profile");

        }
        else{
            getSupportActionBar().setTitle("Create Your Profile");
        }

        Button Button_CreateOK = (Button)findViewById(R.id.Button_ProfileOK);
        Button_CreateOK.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if(!(mPicFile == null)){
                    Profile newProfile = new Profile(userName, mPicFile.getPath(), Text_Name.getText().toString(), Text_Home.getText().toString(), Text_Bio.getText().toString());
                    Intent intent = getIntent();
                    String userName = intent.getExtras().getString(Activity_Login.Key_UserName);
                    Log.d(TAG, "Current User name is " + userName);

                    User user = new User();
                    user.setProfile(newProfile); // set corresponding components for a user's data
                    user.setUserName(userName);

                    Collection.addProfile(user); // insert PROFILE into table

                    intent = new Intent(Activity_CreateProfile.this, Activity_MainMenu.class);
                    intent.putExtra(Activity_Login.Key_UserName, userName);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please fill up the blanks for creating your profile",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button Button_Cancel = (Button)findViewById(R.id.Button_ProfileCancel);
        Button_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String userName = intent.getExtras().getString(Activity_Login.Key_UserName);

                intent = new Intent(Activity_CreateProfile.this, Activity_MainMenu.class);
                intent.putExtra(Activity_Login.Key_UserName, userName); // go to Main Menu activity
                startActivity(intent);
                finish();
            }
        });

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult() is called in CreateProfile class");
        if(resultCode == Activity.RESULT_OK) {

//            profile_Pic= (ImageView)findViewById(R.id.profile_Image);

            Bitmap bitmap_Pic = SetBitmap.getBitmap(mPicFile.getPath(), profile_Pic.getWidth(),profile_Pic.getHeight());
            Log.d(TAG,"Path to photo is " + mPicFile.getPath());

            profile_Pic.setImageBitmap(bitmap_Pic);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_type1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        boolean isSelected;

        if(item.getItemId() == R.id.menu_CreateProfile){
            Toast.makeText(getApplicationContext(), "You are now in Create Profile Page", Toast.LENGTH_LONG).show();
            isSelected = true;
        }

        else if(item.getItemId() == R.id.menu_LogOut){

            Intent intent = new Intent(Activity_CreateProfile.this, Activity_Login.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d(TAG, "Menu Log out is clicked");
            isSelected = true;
        }
        else if(item.getItemId() == R.id.menu_MainMenu){
            Intent intent = new Intent(Activity_CreateProfile.this, Activity_MainMenu.class);
            intent.putExtra(Activity_Login.Key_UserName, userName);
            startActivity(intent);

            Log.d("My tag", "Menu gotoMainMenu is clicked");
            isSelected = true;
        }
        else if(item.getItemId() == R.id.menu_ShowFavorite){
            Intent intent = new Intent(Activity_CreateProfile.this, Activity_Favorite.class);
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