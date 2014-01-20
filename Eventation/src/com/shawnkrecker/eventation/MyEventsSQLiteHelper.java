package com.shawnkrecker.eventation;

import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteOpenHelper;

public class MyEventsSQLiteHelper extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 3;

	private static final String MY_EVENTS_TABLENAME = "myevents";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_GROUP_NAME = "groupname";
	private static final String COLUMN_EVENT_NAME = "eventname";
	private static final String COLUMN_EVENT_DESCRIPTION = "myeventdescription";
	private static final String COLUMN_ATTENDING_COUNT = "attendingcount";
	private static final String COLUMN_EVENT_DATE = "eventdate";
	private static final String COLUMN_EVENT_TIME = "eventtime";
	
	
	private static final String DATABASE_NAME = "myevents.db";
	
	private static final String DATABASE_CREATE = "CREATE TABLE " 
			+ MY_EVENTS_TABLENAME + " (" + COLUMN_ID + " INTEGER, "
			+ COLUMN_GROUP_NAME + " TEXT, "
			+ COLUMN_EVENT_NAME + " TEXT, "
			+ COLUMN_EVENT_DESCRIPTION + " TEXT, "
			+ COLUMN_EVENT_DATE + " TEXT, "
			+ COLUMN_EVENT_TIME + " TEXT, "
			+ COLUMN_ATTENDING_COUNT + " INTEGER)";


	public MyEventsSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + MY_EVENTS_TABLENAME);
	}


}
