package com.example.ajay.myevent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AJAY on 04-Nov-16.
 */
public class MyDatabaseHandler extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "eventHandler";

    // Event table name
    private static final String TABLE_EVENT = "event";

    // Event Table Columns names
    private static final String KEY_TITLE = "title";
    private static final String KEY_DETAILS = "details";


    public MyDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENT + "("
                + KEY_TITLE + " TEXT PRIMARY KEY," + KEY_DETAILS + " TEXT )";
        db.execSQL(CREATE_EVENT_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);

        // Create tables again
        onCreate(db);
    }

    // resetting database
    public void onReset(SQLiteDatabase db) {
        // Drop older table if existed
        db.execSQL("DELETE FROM " + TABLE_EVENT);

        // Create tables again
        //onCreate(db);
    }

    // Adding new event
    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, event.getTitle()); // Event text
        values.put(KEY_DETAILS, event.getDetails()); // Event details
        // Inserting Row
        db.insert(TABLE_EVENT, null, values);
        db.close(); // Closing database connection

    }

    public Event getEvent(String title) {
        SQLiteDatabase db = this.getReadableDatabase();

        String get_event_query = "SELECT * FROM " + TABLE_EVENT + " WHERE "+KEY_TITLE+" = "+title;
        Cursor cursor = db.rawQuery(get_event_query,null);

        if (cursor != null)
            cursor.moveToFirst();

        Event event = new Event(cursor.getString(0),cursor.getString(1));
        // return event
        return event;
    }

    public int deleteEvent(String title) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = new String[]{title};
        int num = db.delete(TABLE_EVENT,"title=?",args);
        return num;

    }

    // Getting All Events
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<Event>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setTitle(cursor.getString(0));
                event.setDetails(cursor.getString(1));

                events.add(event);
            } while (cursor.moveToNext());
        }

        // return events list
        return events;
    }

}
