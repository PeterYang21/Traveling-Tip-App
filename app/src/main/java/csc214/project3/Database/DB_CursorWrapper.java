package csc214.project3.Database;


import android.database.Cursor;
import android.database.CursorWrapper;

import org.w3c.dom.Attr;

import csc214.project3.Model.*;

public class DB_CursorWrapper extends CursorWrapper{

    public DB_CursorWrapper(Cursor cursor){
        super(cursor);
    }

    public City getCity(){
        if(getCount() == 0){ // if no such item exits in database, cursor wrapper returns null
            return null;
        }

        String CityName = getString(getColumnIndex(DB_Schema.CityTable.Columns.CITY_NAME));
        int ImageResource = getInt(getColumnIndex(DB_Schema.CityTable.Columns.CITY_IMAGE));

        return new City(CityName, ImageResource);
    }

    public Account getAccount(){

        if(getCount() == 0){ // if no such item exits in database, cursor wrapper returns null
            return null;
        }

        String userName = getString(getColumnIndex(DB_Schema.AccountTable.Columns.USERNAME));
        String pwd = getString(getColumnIndex(DB_Schema.AccountTable.Columns.PASSWORD));

        return new Account(userName, pwd);
    }

    public Profile getProfile(){
        if(getCount() == 0){ // if no such item exits in database, cursor wrapper returns null
            return null;
        }
        String userName = getString(getColumnIndex(DB_Schema.ProfileTable.Columns.USERNAME));
        String pathToPhoto = getString(getColumnIndex(DB_Schema.ProfileTable.Columns.PATH_PHOTO));
        String FullName = getString(getColumnIndex(DB_Schema.ProfileTable.Columns.NAME));
        String homeTown = getString(getColumnIndex(DB_Schema.ProfileTable.Columns.HOMETOWN));
        String Bio = getString(getColumnIndex(DB_Schema.ProfileTable.Columns.BIO));

        return new Profile(userName, pathToPhoto, FullName,homeTown, Bio);
    }

    public Attraction getAttraction(){
        if(getCount() == 0){
            return null;
        }
        String AttractionName = getString(getColumnIndex(DB_Schema.AttractionTable.Columns.ATTRACTION_NAME));
        String city = getString(getColumnIndex(DB_Schema.AttractionTable.Columns.CITY));
        int preview = getInt(getColumnIndex(DB_Schema.AttractionTable.Columns.PREVIEW_IMAGE));
        int image01 = getInt(getColumnIndex(DB_Schema.AttractionTable.Columns.IMAGE01));
        int image02 = getInt(getColumnIndex(DB_Schema.AttractionTable.Columns.IMAGE02));
        int image03 = getInt(getColumnIndex(DB_Schema.AttractionTable.Columns.IMAGE03));
        int info = getInt(getColumnIndex(DB_Schema.AttractionTable.Columns.INFO));

        return new Attraction(AttractionName,city,preview,image01,image02,image03,info);
    }

}
