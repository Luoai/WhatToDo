package com.ac.behrendapp.whattodo;

import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.io.File;
import java.security.Key;


// hahahaha
public class MyEventDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "events.db";
    private static final String TABLE_EVENTS = "events";
    private static final String COLUMN_ID = "eventId";
    private static final String COLUMN_ENAME = "eName";

    private static final String COLUMN_ESTARTTIME = "eStartTime";
    private static final String COLUMN_ESTARTHOUR = "eStartHour";
    private static final String COLUMN_ESTARTMIN = "eStartMin";

    private static final String COLUMN_EENDTIME = "eEndTime";
    private static final String COLUMN_EENDHOUR = "eEndHour";
    private static final String COLUMN_EENDMIN = "eEndMin";

    private static final String COLUMN_ELOC = "eLoc";
    private static final String COLUMN_EDATE = "eDate";
    private static final String COLUMN_EYEAR = "eYear";
    private static final String COLUMN_EDAY = "eDay";
    private static final String COLUMN_EMONTH = "eMonth";
    private static final String COLUMN_DESCRITION = "description";

    //Constructor

    public MyEventDBHandler(Context context,String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,DATABASE_NAME,factory,DATABASE_VERSION);
    }

    public boolean doesDatabaseExist(ContextWrapper context) {
        File dbFile = context.getFileStreamPath("events.db");
        return dbFile.exists();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // build columns in the db
        String query = "CREATE TABLE " + TABLE_EVENTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + //SQL will automatically genereate ID
                COLUMN_ENAME + "  TEXT, " +
                COLUMN_ELOC + " TEXT, " +
                COLUMN_EDATE + " INTEGER, " +
                COLUMN_EYEAR + " INTEGER, " +
                COLUMN_EMONTH + " INTEGER, " +
                COLUMN_EDAY + " INTEGER, " +
                COLUMN_ESTARTTIME + " TEXT, " +
                COLUMN_ESTARTHOUR + " INTEGER, " +
                COLUMN_ESTARTMIN + " INTEGER, " +
                COLUMN_EENDTIME + " TEXT, " +
                COLUMN_EENDHOUR + " INTEGER, " +
                COLUMN_EENDMIN + " INTEGER, " +
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
        values.put(COLUMN_EDAY,event.getEDay());
        values.put(COLUMN_EMONTH,event.getEMonth());
        values.put(COLUMN_EYEAR,event.getEYear());
        values.put(COLUMN_ESTARTTIME,event.getEStartTime());
        values.put(COLUMN_ESTARTHOUR,event.getEStartHour());
        values.put(COLUMN_ESTARTMIN,event.getEStartMin());
        values.put(COLUMN_EENDHOUR,event.getEEndHour());
        values.put(COLUMN_EENDMIN,event.getEEndMin());
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
        //String query = "SELECT * FROM " + TABLE_EVENTS +
        //    " WHERE 1";

        //Cursor point to a location in your results
        //Cursor c = db.rawQuery(query, null);
        String[] columns = {COLUMN_ID, COLUMN_ENAME, COLUMN_ELOC, COLUMN_EDATE};
        Cursor c = db.query(TABLE_EVENTS, columns, null, null, null, null, COLUMN_EDAY + " DESC");
        //Move to first row in your result;
        c.moveToFirst();

        do {
            if (c.getString(1) != null) {
                dbString += c.getString(1) + "\t" +
                        c.getString(3) + "\t" +
                        c.getString(2);
                dbString += "\n";
            }
        } while (c.moveToNext());
        db.close();
        return dbString;
    }

    //Print out the databaase as a string
    public String getAllBurkeEvent(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_EVENTS +
                " WHERE " + COLUMN_ELOC + " = \'Burke\'";

        // http://www.androidhive.info/2012/05/how-to-connect-android-with-php-mysql/
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to first row in your result
        c.moveToFirst();

        do {
            if (c.getString(1) != null) {
                dbString += c.getString(1);
                dbString += "\n";
            }
        } while (c.moveToNext());
        db.close();
        return dbString;
    }

}


