package com.ac.behrendapp.whattodo;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;


// hahahaha
public class MyEventDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "events.db";
    private static final String TABLE_EVENTS = "events";
    private static final String COLUMN_ID = "eventId";
    private static final String COLUMN_ENAME = "eName";
    private static final String COLUMN_ESTARTTIME="eStartTime";
    private static final String COLUMN_EENDTIME="eEndTime";
    private static final String COLUMN_ELOC = "eLoc";
    private static final String COLUMN_EDATE = "eDate";
    private static final String COLUMN_DESCRITION = "description";

    //Constructor
    public MyEventDBHandler(Context context,String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // When create, what you want to do here
        String query = "CREATE TABLE" + TABLE_EVENTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " + //SQL will automatically genereate ID
                COLUMN_ENAME + "  TEXT " +
                COLUMN_ELOC + " TEXT " +
                COLUMN_EDATE + " TEXT " +
                COLUMN_ESTARTTIME + " TEXT " +
                COLUMN_EENDTIME + " TEXT " +
                COLUMN_DESCRITION + " TEXT " +
                ");";
        db.execSQL(query);//pass query to SQL

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }


}
