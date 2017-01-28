package com.beginner.doughboys;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by John Boy on 1/23/2017.
 */

public class RestaurantDatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "reviews.db";
    private static final String TABLE_NAME = "restaurant_database";
    private static final String COLUMN_ID = "review_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_RESTAURANT_NAME = "restaurant_name";
    private static final String COLUMN_RESTAURANT_CITY = "restaurant_city";
    private static final String COLUMN_OVERALL_RATING = "rating";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table " + TABLE_NAME +
            " (review_id integer primary key autoincrement, username text not null, restaurant_name text not null, " +
            "restaurant_city text not null, rating integer not null);";

    public RestaurantDatabaseHelper(Context context) {
        super(context , DATABASE_NAME , null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query =  "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public Cursor getAllData() {
        db = getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return result;
    }


    public boolean insertReview(Review r) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_USERNAME, r.getUsername());
        values.put(COLUMN_RESTAURANT_NAME, r.getRestaurantName());
        values.put(COLUMN_RESTAURANT_CITY, r.getRestaurantCity());
        values.put(COLUMN_OVERALL_RATING, r.getRating());

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        if(result==-1) {
            return false;
        } else {
            return true;
        }
    }

}
