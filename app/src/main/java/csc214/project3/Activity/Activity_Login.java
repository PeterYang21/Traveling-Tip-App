package csc214.project3.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import csc214.project3.Model.Account;
import csc214.project3.Model.Collection;
import csc214.project3.Database.*;
import csc214.project3.R;

public class Activity_Login extends AppCompatActivity {

    public static final String TAG = "My Tag";
    public static SQLiteDatabase myDatabase;
    public static EditText Text_UserName;
    public static EditText Text_Password;

    public static String Key_UserName = "Key_UserName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Account Log In");

        Text_UserName = (EditText)findViewById(R.id.Text_EnterUserName);
        Text_Password = (EditText)findViewById(R.id.Text_EnterPassword);

        myDatabase = Collection.getDatabase(getApplicationContext());

        Button Button_Login = (Button)findViewById(R.id.Button_Login);
        Button_Login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                String inputUserName = Text_UserName.getText().toString();
                String inputPWD = Text_Password.getText().toString();

                if(verifyAccount(inputUserName, inputPWD)){ // allow to proceed into the application
                    Intent intent = new Intent(Activity_Login.this, Activity_MainMenu.class);
                    intent.putExtra(Key_UserName, inputUserName);
                    startActivity(intent);
                    finish();
                }
            }
        });

        Button Button_Create = (Button)findViewById(R.id.Button_Create);
        Button_Create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){ // create a new account
                Intent intent = new Intent(Activity_Login.this, Activity_CreateAccount.class);
                startActivity(intent);
            }
        });

    }

    private static DB_CursorWrapper Query(String WHERE, String[] args){ // query the database
        Cursor mCursor =
                myDatabase.query(
                        DB_Schema.AccountTable.TABLE_NAME, // Select * from
                        null,
                        WHERE,
                        args,
                        null, null, null);

        return new DB_CursorWrapper(mCursor); // return a cursor for this database
    }

    public boolean verifyAccount(String inputUserName, String inputPWD){
        String WHERE = DB_Schema.AccountTable.Columns.USERNAME + "=?";
        String []args = {inputUserName};
        Log.d(TAG, "Input user name is " + inputUserName);

        DB_CursorWrapper mWrapper = Query(WHERE, args);
        mWrapper.moveToFirst();

        if(mWrapper.getAccount() == null){
            Toast.makeText(getApplicationContext(), "User does not exist!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "User does not exist");
        }
        else{
            Log.d(TAG, "User exists");
            Account acc = mWrapper.getAccount();

            if(acc.getPassword().equals(inputPWD)){
                Log.d(TAG, "Correct Password");
                return true;
            }
            else{
                Toast.makeText(getApplicationContext(), "Wrong Password!", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Wrong Password");
            }
        }
        mWrapper.close();
        return false;
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy is called");
    }
}
