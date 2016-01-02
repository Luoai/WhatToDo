package com.ac.behrendapp.whattodo;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;


// hahahaha
public class MyEventDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "events.db";
    private static final String TABLE_EVENTS = "events";
    private static final String COLUMN_ID = "eventId";
    private static final String COLUMN_ENAME = "eName";
    private static final String COLUMN_ESTARTTIME = "eStartTime";
    private static final String COLUMN_EENDTIME = "eEndTime";
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
        String query = "CREATE TABLE " + TABLE_EVENTS + "(" +
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);//delete the exsited table
        onCreate(db);
    }

    //Add a new row (a new event object) to database
    public void addEvent(Event event){

        // Create a new content(key,value) from event object
        ContentValues values = new ContentValues(); //something like dictionary
        values.put(COLUMN_ENAME,event.getEname());
        values.put(COLUMN_ELOC,event.getELoc());
        values.put(COLUMN_EDATE,event.getEDate());
        values.put(COLUMN_ESTARTTIME,event.getEStartTime());
        values.put(COLUMN_EENDTIME,event.getEEndTime());
        values.put(COLUMN_DESCRITION,event.getDescription());

        //insert it into db
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EVENTS, null, values);

    }

    //Delete a row (a Event Object) from database
    public void deleteEvent(String eventName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EVENTS + " WHERE "
                + COLUMN_ENAME + " =\"" + eventName +
                "\"");
    }

    //Print out the databaase as a string
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_EVENTS +
                " WHERE 1";

        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query,null);
        //Move to first row in your result
        c.moveToFirst();

        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("eventname")) != null) {
                dbString += c.getString(c.getColumnIndex("eventname"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
        }

}


