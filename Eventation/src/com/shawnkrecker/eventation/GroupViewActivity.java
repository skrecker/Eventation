package com.shawnkrecker.eventation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GroupViewActivity extends Activity {
	
	private String groupName;
	private String groupDescription;
	
	private MyDBHelper helper;
	
	private SQLiteDatabase db;
	
	private Cursor cursor;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_view);
		
		
		Bundle bundle = getIntent().getExtras();
		String id = bundle.getString("id");
		
		helper = new MyDBHelper(this);	
		db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery("SELECT * FROM mygroups WHERE _id = '" + id  +"'",null); 
		
		cursor.moveToFirst();
		groupName = cursor.getString(0);
		groupDescription = cursor.getString(1);
		
		TextView nameView = (TextView) findViewById(R.id.textView1);
		nameView.setText(groupName);
		
		TextView descriptionView = (TextView) findViewById(R.id.textView4);
		descriptionView.setText(groupDescription);
		
		cursor.close();
		
		db.close();
		helper.close();
		
		Button createEventButton = (Button) findViewById(R.id.button1);
		
		createEventButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				createEvent();
								
			}
		});
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_view, menu);
		return true;
	}
	
	public void createEvent(){
		Intent intent = new Intent(this,CreateEventActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("groupName",groupName);
		intent.putExtras(bundle);
		startActivity(intent);
	}


}
