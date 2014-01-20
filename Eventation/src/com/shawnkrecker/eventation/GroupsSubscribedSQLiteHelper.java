package com.shawnkrecker.eventation;

import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class GroupsSubscribedSQLiteHelper extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 3;

	private static final String SUBSCRIBED_GROUPS_TABLENAME = "subscribed_groups";
	private static final String COLUMN_GROUPNAME = "_id";
	private static final String COLUMN_GROUPDESCRIPTION = "group_description";
	
	private static final String DATABASE_NAME = "subscribed_groups.db";
	
	private static final String DATABASE_CREATE = "CREATE TABLE " 
			+ SUBSCRIBED_GROUPS_TABLENAME + " (" + COLUMN_GROUPNAME + " TEXT, "
			+ COLUMN_GROUPDESCRIPTION + " TEXT)";


	public GroupsSubscribedSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
			
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + SUBSCRIBED_GROUPS_TABLENAME);
	}



}
