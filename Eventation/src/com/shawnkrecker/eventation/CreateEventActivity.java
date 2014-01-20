package com.shawnkrecker.eventation;


import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.shawnkrecker.eventation.eventendpoint.Eventendpoint;
import com.shawnkrecker.eventation.eventendpoint.model.Event;

import java.util.Date;

import com.shawnkrecker.eventation.groupendpoint.Groupendpoint;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


public class CreateEventActivity extends Activity {
	
	private static final String MY_EVENTS_TABLENAME = "myevents";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_GROUP_NAME = "groupname";
	private static final String COLUMN_EVENT_NAME = "eventname";
	private static final String COLUMN_EVENT_DESCRIPTION = "myeventdescription";
	private static final String COLUMN_ATTENDING_COUNT = "attendingcount";
	private static final String COLUMN_EVENT_MONTH = "eventmonth";
	private static final String COLUMN_EVENT_DAY = "eventday";
	private static final String COLUMN_EVENT_YEAR = "eventyear";
	private static final String COLUMN_EVENT_TIME = "eventtime";
	
	private MyDBHelper helper;	
	private SQLiteDatabase db;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
			
		Button button = (Button) findViewById(R.id.button1);
		
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker1);
		datePicker.setCalendarViewShown(false);
		
		
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				createEvent();
			}
		});
	}
	
	public void createEvent(){
		
		helper = new MyDBHelper(getApplicationContext());
		db = helper.getWritableDatabase();
		
		Bundle b = getIntent().getExtras();
		String groupName = b.getString("groupName");
		
		EditText eventNameText = (EditText) findViewById(R.id.editText1);
		String eventName = eventNameText.getText().toString();
		
		EditText eventDescriptionText = (EditText) findViewById(R.id.editText2);
		String eventDescription = eventDescriptionText.getText().toString();
		
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker1);
		datePicker.setCalendarViewShown(false);
		
		int day = datePicker.getDayOfMonth();
		int month = datePicker.getMonth();
		int year = datePicker.getYear();
		
		
		TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker1);
		int hour = timePicker.getCurrentHour();
		int minute = timePicker.getCurrentMinute();
		
		Log.d("onClick error", "eventName is " + eventName);
		Log.d("onClick error", "eventDescription is " + eventDescription);
		Log.d("onClick error", "day is " + day);
		Log.d("onClick error", "month is " + month);
		Log.d("onClick error", "year is " + year);
		Log.d("onClick error", "hour is " + hour);
		Log.d("onClick error", "minute is " + minute);
		
		Bundle bundle = new Bundle();
		bundle.putString("groupName", groupName);
		bundle.putString("eventName", eventName);
		bundle.putString("eventDescription", eventDescription);
		bundle.putInt("day", day);
		bundle.putInt("month", month);
		bundle.putInt("year", year);
		bundle.putInt("hour", hour);
		bundle.putInt("minute", minute);
		
		String dateString = month + 1 + "/" + day + "/" + year;
		
		String timeString = hour + ":" + minute;
		
		Event event = null;
		
		
		try{
			 event = new CreateEventTask().execute(bundle).get();
			 			 
			 				 
		}catch(ExecutionException ex){
			
			
		}catch(InterruptedException ex2){
			
		}
		
		if(event != null){

			
			try{
				
				Log.d("Eventation Create Event application context", getApplicationContext().toString());
				
				helper = new MyDBHelper(getApplicationContext());
								
				db = helper.getWritableDatabase();
														
				ContentValues values = new ContentValues();
				values.put(COLUMN_ID,event.getId());
				values.put(COLUMN_GROUP_NAME, event.getGroupName());
				values.put(COLUMN_EVENT_NAME, event.getEventName());
				values.put(COLUMN_EVENT_DESCRIPTION, event.getEventDescription());
				values.put(COLUMN_EVENT_MONTH, month);
				values.put(COLUMN_EVENT_DAY, day);
				values.put(COLUMN_EVENT_YEAR, year);
				values.put(COLUMN_EVENT_TIME, timeString);
				values.put(COLUMN_ATTENDING_COUNT, 0);
				db.insert(MY_EVENTS_TABLENAME,null,values);
			
			}catch(SQLiteCantOpenDatabaseException ex){
				
			}
					
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			
			finish();
			
			
		}
	
	}
	
	@Override
	public void onPause(){
		super.onPause();
		helper.close();
		db.close();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		helper = new MyDBHelper(getApplicationContext());	
		db = helper.getWritableDatabase();
		
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		helper.close();
		db.close();
	}
	
	
	
	public void reload(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		 
		 finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_event, menu);
		return true;
	}
	
	
	private class CreateEventTask extends AsyncTask<Bundle, Void, Event>{
		public Event doInBackground(Bundle... b){
			
			Bundle bundle = b[0];
			
			String groupName = bundle.getString("groupName");
			String eventName = bundle.getString("eventName");
			String eventDescription = bundle.getString("eventDescription");
			
			int day = bundle.getInt("day");
			int month = bundle.getInt("month");
			int year = bundle.getInt("year");
			
			int hour = bundle.getInt("hour");
			int minute = bundle.getInt("minute");
			
			Calendar calendar = new GregorianCalendar();
			calendar.set(year, month, day,hour, minute);
					
			Date date = new Date(calendar.getTimeInMillis());
			DateTime dateTime = new DateTime(date);
			
			
			Event event = new Event();

			event.setId(new Random().nextLong());
			event.setGroupName(groupName);
			event.setEventName(eventName);
			event.setEventDescription(eventDescription);
			event.setEventDate(dateTime); 
			event.setAttendingCount(0);		
			
			Log.d("Event add error", event.getEventName());
			Log.d("Event add error", event.getEventDescription());
			Log.d("Event add error", event.getEventDate().toString());
			Log.d("Event add error", event.getAttendingCount().toString());
			
			
			try{
				Eventendpoint.Builder builder = new Eventendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
				Eventendpoint gEnd = builder.build();
				
				return gEnd.insertEvent(event).execute();
				
			}catch(com.google.api.client.googleapis.json.GoogleJsonResponseException ex1){
				Log.e("failed", ex1.toString());
			
			}catch(IOException ex){
				Log.e("failed", ex.toString());
			}
			
			return event;
		}
	}

}
