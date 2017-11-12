package csc214.project3.Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

import csc214.project3.Database.DB_CursorWrapper;
import csc214.project3.Database.DB_Helper;
import csc214.project3.Database.DB_Schema;

public class Collection {

    private static Context myContext;
    private static SQLiteDatabase myDatabase;

    private static final String TAG = "My Tag";

    public static SQLiteDatabase getDatabase(Context context){
        myContext = context.getApplicationContext();
        myDatabase = new DB_Helper(myContext).getWritableDatabase(); // to create or upgrade the database;

        return myDatabase;
    }

    public static void insertCity(City city, SQLiteDatabase db){
        ContentValues mValues = getValues_City(city);
        Log.d(TAG, ""+mValues);
        myDatabase = db;
        myDatabase.insert(DB_Schema.CityTable.TABLE_NAME, null, mValues);
    }

    private static ContentValues getValues_City(City city){
        ContentValues mValues = new ContentValues();

        // map all the attributes of an object to the content values
        mValues.put(DB_Schema.CityTable.Columns.CITY_NAME, city.getCityName());
        mValues.put(DB_Schema.CityTable.Columns.CITY_IMAGE, city.getCityImageResource());

        return mValues;
    }

    public static void insertAttraction(Attraction att, SQLiteDatabase db){
        ContentValues mValues = getValues_Attraction(att);
        Log.d(TAG, ""+mValues);
        myDatabase = db;
        myDatabase.insert(DB_Schema.AttractionTable.TABLE_NAME, null, mValues);
    }

    private static ContentValues getValues_Attraction(Attraction att){
        ContentValues mValues = new ContentValues();

        mValues.put(DB_Schema.AttractionTable.Columns.ATTRACTION_NAME,att.getName());
        mValues.put(DB_Schema.AttractionTable.Columns.PREVIEW_IMAGE, att.getPreviewImage());
        mValues.put(DB_Schema.AttractionTable.Columns.IMAGE01, att.getImage01());
        mValues.put(DB_Schema.AttractionTable.Columns.IMAGE02, att.getImage02());
        mValues.put(DB_Schema.AttractionTable.Columns.IMAGE03, att.getImage03());
        mValues.put(DB_Schema.AttractionTable.Columns.INFO, att.getInfo());
        mValues.put(DB_Schema.AttractionTable.Columns.CITY, att.getCity());

        return mValues;
    }

    public static void addAccount(User user){ // insert a new account to database

        ContentValues mValues = getValues_Account(user); // get the attributes from user and insert into database

        Log.d(TAG, "Account Values is " + mValues);
        myDatabase.insert(DB_Schema.AccountTable.TABLE_NAME, null, mValues);
    }

    private static ContentValues getValues_Account(User user){
        ContentValues mValues = new ContentValues();

        // map all the attributes of an object to the content values
        mValues.put(DB_Schema.AccountTable.Columns.USERNAME, user.getAccount().getUserName());
        Log.d(TAG, "Account user name is " + user.getAccount().getUserName());
        mValues.put(DB_Schema.AccountTable.Columns.PASSWORD, user.getAccount().getPassword());
        Log.d(TAG, "Account pwd is " + user.getAccount().getPassword());
        return mValues;
    }

    public static void addProfile(User user){ // insert PROFILE into profile table
        ContentValues mValues = getValues_Profile(user);

        String[] whereArgs = {user.getUserName()};
        Log.d(TAG, "Update the table: " + user.getUserName());

        int rows = myDatabase.update(DB_Schema.ProfileTable.TABLE_NAME, mValues, DB_Schema.ProfileTable.Columns.USERNAME+" =?",whereArgs);

        if(rows== 0){
            Log.d(TAG, "Insert into Profile table");
            myDatabase.insert(DB_Schema.ProfileTable.TABLE_NAME, null, mValues);
        }
//        Log.d(TAG, "Profile Values is " + mValues);
//
//        String SQL = "Select " + DB_Schema.ProfileTable.Columns.USERNAME + " From " + DB_Schema.ProfileTable.TABLE_NAME
//                + " Where " + DB_Schema.ProfileTable.Columns.USERNAME + " =?";
//        Log.d(TAG, "Update SQL: " + SQL);
//        Log.d(TAG, "Profile user name is " + user.getUserName());
//
//        Cursor cursor = myDatabase.rawQuery(SQL, new String[]{user.getUserName()});
//        cursor.moveToFirst();
//        DB_CursorWrapper mWrapper = new DB_CursorWrapper(cursor);
//        mWrapper.moveToFirst();
//
//        if(!mWrapper.isAfterLast()){
//            if(mWrapper.getProfile() == null){
//                myDatabase.insert(DB_Schema.ProfileTable.TABLE_NAME, null, mValues);
//            }
//            else{
//                String[] whereArgs = {user.getUserName()};
//                Log.d(TAG, "Update the table: " + user.getUserName());
//
//                myDatabase.update(DB_Schema.ProfileTable.TABLE_NAME, mValues, DB_Schema.ProfileTable.Columns.USERNAME+" =?",whereArgs);
//            }
//        }

    }

    private static ContentValues getValues_Profile(User user) {
        ContentValues mValues = new ContentValues();

        // map all the attributes of an object to the content values
        mValues.put(DB_Schema.ProfileTable.Columns.USERNAME, user.getUserName());
        mValues.put(DB_Schema.ProfileTable.Columns.PATH_PHOTO, user.getProfile().getPathToPhoto());
        mValues.put(DB_Schema.ProfileTable.Columns.NAME, user.getProfile().getFullName());
        mValues.put(DB_Schema.ProfileTable.Columns.HOMETOWN, user.getProfile().getHomeTown());
        mValues.put(DB_Schema.ProfileTable.Columns.BIO, user.getProfile().getBio());

        return mValues;
    }

    private static ContentValues getValues_Favor(User user, String cityName){
        ContentValues mValues = new ContentValues();

        mValues.put(DB_Schema.FavoriteTable.Columns.USERNAME, user.getUserName());
        mValues.put(DB_Schema.FavoriteTable.Columns.FAVORITE, cityName);

        return mValues;
    }


    public static Profile checkProfile(String userName){
//        String SQL = "Select " + DB_Schema.ProfileTable.Columns.USERNAME + " From " + DB_Schema.ProfileTable.TABLE_NAME
//                + " Where " + DB_Schema.ProfileTable.Columns.USERNAME + " = ?";
//        Log.d(TAG, "Selection SQL: " + SQL);

        String WHERE = DB_Schema.ProfileTable.Columns.USERNAME+" =?";
        String[] args = new String[]{userName};
        Cursor cursor =
                myDatabase.query(
                        DB_Schema.ProfileTable.TABLE_NAME, // Select * from
                        null,
                        WHERE,
                        args,
                        null, null, null);

//        Cursor cursor = myDatabase.rawQuery(SQL, str);
//        cursor.moveToFirst();
        DB_CursorWrapper mWrapper = new DB_CursorWrapper(cursor);
        mWrapper.moveToFirst();

        if(!mWrapper.isAfterLast()){
            Profile profile = mWrapper.getProfile();

            mWrapper.moveToNext();

            return profile; // profile already exists
        }
        mWrapper.close();

        return null;
    }

    public static boolean check_duplicateAccount(String userName){
        String WHERE = DB_Schema.ProfileTable.Columns.USERNAME+" =?";
        String[] args = new String[]{userName};
        Cursor cursor =
                myDatabase.query(
                        DB_Schema.AccountTable.TABLE_NAME, // Select * from
                        null,
                        WHERE,
                        args,
                        null, null, null);
        DB_CursorWrapper mWrapper = new DB_CursorWrapper(cursor);
        mWrapper.moveToFirst();

        if(!mWrapper.isAfterLast()){
            Account acc = mWrapper.getAccount();

            mWrapper.moveToNext();

            if(acc != null) { // account already exists
                return true;
            }
        }
        mWrapper.close();

        return false;
    }

    public static void insertFavorite(){
        // insert favorite table dialog confirm adding preference or logout
    }
}
