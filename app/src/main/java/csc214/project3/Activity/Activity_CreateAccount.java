package csc214.project3.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import csc214.project3.Model.Account;
import csc214.project3.Model.Collection;
import csc214.project3.Model.User;
import csc214.project3.R;

public class Activity_CreateAccount extends AppCompatActivity {
    public static final String TAG = "My Tag";
    public static EditText userText;
    public static EditText pwdText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().setTitle("Create Your Account");

        userText = (EditText)findViewById(R.id.Text_CreateUserName);
        pwdText = (EditText)findViewById(R.id.Text_CreatePassword);

        Button CreateOK = (Button)findViewById(R.id.Button_CreateOK);
        CreateOK.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG, "New account is created in Activity_CreateAccount");

                if(userText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter your user name", Toast.LENGTH_SHORT).show();
                }
                else if(pwdText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please enter a valid password", Toast.LENGTH_SHORT).show();
                }
                else{
                    // check if user name has already existed
                    if(!Collection.check_duplicateAccount(userText.getText().toString())){
                        Account newAccount = new Account(userText.getText().toString(), pwdText.getText().toString());
                        User newUser = new User(); // create a new user
                        newUser.setAccount(newAccount);

                        Collection.addAccount(newUser); // add new ACCOUNT to database

                        Intent intent = new Intent(Activity_CreateAccount.this, Activity_Login.class);
                        startActivity(intent);
                    }
                    else{
                        Log.d(TAG, "Duplicate Account Occurs");
                        Toast.makeText(getApplicationContext(),"User Name has already existed\nPlease pick a new one",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button CreateCancel = (Button)findViewById(R.id.Button_CreateCancel);
        CreateCancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Activity_CreateAccount.this, Activity_Login.class);
                startActivity(intent);
            }
        });

    }

}