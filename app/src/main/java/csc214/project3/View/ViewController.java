package csc214.project3.View;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import csc214.project3.Database.DB_CursorWrapper;
import csc214.project3.Database.DB_Schema;
import csc214.project3.Model.Attraction;
import csc214.project3.Model.City;

public class ViewController {

    private static SQLiteDatabase myDatabase;

    public static ArrayList<City> getCollection_City(SQLiteDatabase db){
        myDatabase = db;
        ArrayList<City> cityList = new ArrayList<>();

        DB_CursorWrapper mWrapper = Query_CityTable(null, null);
        mWrapper.moveToFirst();

        while(!mWrapper.isAfterLast()){
            City mCity = mWrapper.getCity();
            cityList.add(mCity);

            mWrapper.moveToNext();
        }
        mWrapper.close();

        return cityList;
    }

    private static DB_CursorWrapper Query_CityTable(String WHERE, String[] args){ // query the database
        Cursor mCursor =
                myDatabase.query(
                        DB_Schema.CityTable.TABLE_NAME, // Select * from
                        null,
                        WHERE,
                        args,
                        null, null, null);

        return new DB_CursorWrapper(mCursor); // return a cursor for this database
    }

    public static ArrayList<Attraction> getCollection_Attraction(SQLiteDatabase db, String city){
        myDatabase = db;
        ArrayList<Attraction> attList = new ArrayList<>();

        String WHERE = DB_Schema.AttractionTable.Columns.CITY+" =?";
        String[] args = new String[]{city};
        Cursor cursor =
                myDatabase.query(
                        DB_Schema.AttractionTable.TABLE_NAME, // Select * from
                        null,
                        WHERE,
                        args,
                        null, null, null);
        DB_CursorWrapper mWrapper = new DB_CursorWrapper(cursor);
        mWrapper.moveToFirst();

        while(!mWrapper.isAfterLast()){
            Attraction att = mWrapper.getAttraction();
            attList.add(att);
            Log.d("My Tag: ", att.getImage01()+ " "+att.getImage02()+" "+att.getImage03());
            mWrapper.moveToNext();
        }
        mWrapper.close();

        return attList;
    }

}
