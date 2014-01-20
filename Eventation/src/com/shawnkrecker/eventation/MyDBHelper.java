package com.shawnkrecker.eventation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 1;
	
	private static final String DATABASE_NAME = "eventation.db";
	
	private static final String MY_EVENTS_TABLENAME = "myevents";
	private static final String MY_GROUPS_TABLENAME = "mygroups";
	private static final String SUBSCRIBED_GROUPS_TABLENAME = "subscribed_groups";
	
	private static final String COLUMN_GROUP_NAME = "_id";
	private static final String COLUMN_GROUP_DESCRIPTION = "group_description";

	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_EVENT_GROUP_NAME = "groupname";
	private static final String COLUMN_EVENT_NAME = "eventname";
	private static final String COLUMN_EVENT_DESCRIPTION = "myeventdescription";
	private static final String COLUMN_ATTENDING_COUNT = "attendingcount";
	private static final String COLUMN_EVENT_MONTH = "eventmonth";
	private static final String COLUMN_EVENT_DAY = "eventday";
	private static final String COLUMN_EVENT_YEAR = "eventyear";
	
	private static final String COLUMN_EVENT_TIME = "eventtime";
	
	
	
	private static final String MY_EVENTS_TABLE_CREATE = "CREATE TABLE " 
			+ MY_EVENTS_TABLENAME + " (" + COLUMN_ID + " INTEGER, "
			+ COLUMN_EVENT_GROUP_NAME + " TEXT, "
			+ COLUMN_EVENT_NAME + " TEXT, "
			+ COLUMN_EVENT_DESCRIPTION + " TEXT, "
			+ COLUMN_EVENT_MONTH + " TEXT, "
			+ COLUMN_EVENT_DAY + " TEXT, "
			+ COLUMN_EVENT_YEAR + " TEXT, "
			+ COLUMN_EVENT_TIME + " TEXT, "
			+ COLUMN_ATTENDING_COUNT + " INTEGER)";
	
	
	private static final String MY_GROUPS_TABLE_CREATE = "CREATE TABLE " 
			+ MY_GROUPS_TABLENAME + " (" + COLUMN_GROUP_NAME + " TEXT, "
			+ COLUMN_GROUP_DESCRIPTION + " TEXT)";
	
	private static final String SUBSCRIBED_GROUPS_TABLE_CREATE = "CREATE TABLE " 
			+ SUBSCRIBED_GROUPS_TABLENAME + " (" + COLUMN_GROUP_NAME + " TEXT, "
			+ COLUMN_GROUP_DESCRIPTION + " TEXT)";
	
	


	public MyDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
			
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(MY_EVENTS_TABLE_CREATE);
		db.execSQL(MY_GROUPS_TABLE_CREATE);
		db.execSQL(SUBSCRIBED_GROUPS_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + SUBSCRIBED_GROUPS_TABLENAME);
	}

	
	
}
