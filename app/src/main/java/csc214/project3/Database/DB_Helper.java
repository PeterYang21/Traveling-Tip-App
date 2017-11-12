package csc214.project3.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import csc214.project3.Model.Attraction;
import csc214.project3.Model.City;
import csc214.project3.Model.Collection;
import csc214.project3.R;

public class DB_Helper extends SQLiteOpenHelper{

    public DB_Helper(Context context) {
        super(context, DB_Schema.DATABASE_NAME, null, DB_Schema.VERSION);
    }

    public static String SQL_City = "create table " // SQL
            + DB_Schema.CityTable.TABLE_NAME
            + "("
            + DB_Schema.CityTable.Columns.CITY_NAME + " text primary key,"
            + DB_Schema.CityTable.Columns.CITY_IMAGE + " integer);";

    public static String SQL_Attrraction = "create table " // SQL
            + DB_Schema.AttractionTable.TABLE_NAME
            + "("
            + DB_Schema.AttractionTable.Columns.ATTRACTION_NAME + " text primary key,"
            + DB_Schema.AttractionTable.Columns.CITY + " text,"
            + DB_Schema.AttractionTable.Columns.PREVIEW_IMAGE + " integer,"
            +DB_Schema.AttractionTable.Columns.INFO + " integer," // string resource
            +DB_Schema.AttractionTable.Columns.IMAGE01 + " integer,"
            +DB_Schema.AttractionTable.Columns.IMAGE02 + " integer,"
            +DB_Schema.AttractionTable.Columns.IMAGE03 + " integer);";

    public static String SQL_Account = "create table "
            + DB_Schema.AccountTable.TABLE_NAME
            + "("
            + DB_Schema.AccountTable.Columns.USERNAME + " text primary key,"
            + DB_Schema.AccountTable.Columns.PASSWORD + " text);";

    public static String SQL_Profile = "create table "
            + DB_Schema.ProfileTable.TABLE_NAME
            + "("
            + DB_Schema.ProfileTable.Columns.USERNAME + " text primary key,"
            + DB_Schema.ProfileTable.Columns.NAME + " text,"
            + DB_Schema.ProfileTable.Columns.HOMETOWN + " text,"
            + DB_Schema.ProfileTable.Columns.PATH_PHOTO + " text,"
            + DB_Schema.ProfileTable.Columns.BIO + " text);";

    public static String SQL_Favorite = "create table "
            +DB_Schema.FavoriteTable.TABLE_NAME + "("
            +DB_Schema.FavoriteTable.Columns.USERNAME + " text,"
            +DB_Schema.FavoriteTable.Columns.FAVORITE + " text,"
            +"Primary Key (" + DB_Schema.FavoriteTable.Columns.USERNAME + ", " + DB_Schema.FavoriteTable.Columns.FAVORITE + "));";

    @Override
    public void onCreate(SQLiteDatabase db) { // create tables with SQL

        db.execSQL(SQL_Account);
        Log.d("My Tag", "SQL_Account is executed: " + SQL_Account);

        db.execSQL(SQL_Profile);
        Log.d("My Tag", "SQL_Profile is executed: " + SQL_Profile);

        db.execSQL(SQL_Favorite);
        Log.d("My Tag", "SQL_Favorite is executed: " + SQL_Favorite);

        db.execSQL(SQL_City);
        Log.d("My Tag", "SQL_CityTable is executed: " + SQL_City);

        db.execSQL(SQL_Attrraction);
        Log.d("My Tag", "SQL_AttractionTable is executed: " + SQL_Attrraction);


        City c1 = new City("Xiamen", R.drawable.xiamen);
        City c2 = new City("Dalian", R.drawable.dalian);
//        Log.d("My Tag", "" + c1.getCityName() + c1.getCityImageResource());

        Collection.insertCity(c1, db); // insert cities
        Collection.insertCity(c2, db);

        Attraction att1 = new Attraction("XingHai Square", "Dalian", R.drawable.xinghai_sqaure,
                R.drawable.dalian_xinghai01,R.drawable.dalian_xinghai02,R.drawable.dalian_xinghai03,R.string.XingHaiSquare);

        Collection.insertAttraction(att1, db);
//        Log.d("My Tag", "" + att1.getPreviewImage()+att1.getImage01()+ att1.getImage02()+att1.getImage03());

        Attraction att_xiamenUniv = new Attraction("Xiamen University", "Xiamen", R.drawable.xiamen_univ01, R.drawable.xiamen_interest,
                R.drawable.xiamen_univ02, R.drawable.xiamen_univ03,R.string.XiamenUiviversity);

        Collection.insertAttraction(att_xiamenUniv, db); // insert attractions for the app


    }

    public DB_Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
