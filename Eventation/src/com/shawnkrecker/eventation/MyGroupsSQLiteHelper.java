package com.shawnkrecker.eventation;

import android.content.Context;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class MyGroupsSQLiteHelper extends SQLiteOpenHelper{
	
	private static final int DATABASE_VERSION = 3;

	private static final String MY_GROUP_TABLENAME = "mygroups";
	private static final String COLUMN_GROUPNAME = "_id";
	private static final String COLUMN_GROUPDESCRIPTION = "mygroupdescription";
	private static final String DATABASE_NAME = "mygroups.db";
	
	private static final String DATABASE_CREATE = "CREATE TABLE " 
			+ MY_GROUP_TABLENAME + " (" + COLUMN_GROUPNAME + " TEXT, "
			+ COLUMN_GROUPDESCRIPTION + " TEXT)";


	public MyGroupsSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
			
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + MY_GROUP_TABLENAME);
	}



}
