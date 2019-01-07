package com.tabian.saveanddisplaysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jerrybananas on 6/1/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "ping_table";
    /*private static final String COL1 = "ID";
    private static final String COL2 = "name";*/
    private static final String COL1 = "id";
    private static final String COL2 = "userid";
    private static final String COL3 = "longitude";
    private static final String COL4 = "latitude";
    private static final String COL5 = "dt";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT)";*/
        String createTable = "CREATE TABLE " + TABLE_NAME + "  ( " + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " VARCHAR(10) NOT NULL , " + COL3 + " FLOAT NOT NULL , " + COL4 + " FLOAT NOT NULL , " + COL5 + " VARCHAR(20) NOT NULL )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String userid, float longitude, float latitude, String dt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, userid);
        contentValues.put(COL3, longitude);
        contentValues.put(COL4, latitude);
        contentValues.put(COL5, dt);
        Log.d(TAG, "addData: Adding " + " [UserId: " + userid + " Longitude: " + longitude + " Latitude: " + latitude + " Timestamp: " + dt + "] to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);
        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     *
     * @return
     */
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     *
     * @param name
     * @return
     */
    public Cursor getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }



    /**
     * Returns only the ID that matches the name passed in
     *
     * @param userid
     * @return
     */
    public Cursor getTimeStamps(String userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL5 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + userid + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }







}
























