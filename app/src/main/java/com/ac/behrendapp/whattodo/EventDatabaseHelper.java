package com.ac.behrendapp.whattodo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;


/**
 * Created by Aiyu on 12/25/2015.
 * refer to https://www.youtube.com/watch?v=NT1qxmqH1eM&list=PLzV8uWUcseN8x0c3q2hRx9X4vbLYSlipb&index=13
 */
public class EventDatabaseHelper extends SQLiteOpenHelper{

    int eventId;
    String eName,eLoc,eTime,description;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "events.db";
    private static final String TABLE_NAME = "events";
    private static final String COLUMN_ID = "eventId";
    private static final String COLUMN_ENAME = "eName";
    private static final String COLUMN_ESTARTTIME="eStartTime";
    private static final String COLUMN_EENDTIME="eEndTime";
    private static final String COLUMN_ELOC = "eLoc";
    private static final String COLUMN_EDATE = "eDate";
    private static final String COLUMN_DESCRITION = "description";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table events (id integer primary key not null auto_increment,";

    //Constructor
    public EventDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    //Insert New event into DB and generate an ID for a new added event
    public void insertEvent(Event e){
        db=this.getWritableDatabase();

        //get ID
        String query="select * from events";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        //Insert
        ContentValues values= new ContentValues();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_ENAME,e.getEname());
        values.put(COLUMN_ESTARTTIME,e.getEStartTime());
        values.put(COLUMN_EENDTIME,e.getEEndTime());
        values.put(COLUMN_EDATE,e.getEDate());
        values.put(COLUMN_ELOC,e.getELoc());
        values.put(COLUMN_DESCRITION,e.getDescription());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXSIT" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

}
