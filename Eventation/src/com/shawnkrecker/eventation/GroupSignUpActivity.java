package com.shawnkrecker.eventation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.shawnkrecker.eventation.groupendpoint.Groupendpoint;
import com.shawnkrecker.eventation.groupendpoint.model.Group;
import com.shawnkrecker.eventation.groupsubscribersendpoint.Groupsubscribersendpoint;
import com.shawnkrecker.eventation.groupsubscribersendpoint.model.GroupSubscribers;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GroupSignUpActivity extends Activity {
	
	public static final String PROPERTY_REG_ID = "registration_id";
	
	private static final String SUBSCRIBED_GROUPS_TABLENAME = "subscribed_groups";
	private static final String COLUMN_GROUP_NAME = "_id";
	private static final String COLUMN_GROUP_DESCRIPTION = "group_description";
	private static final String DATABASE_NAME = "eventation.db";
	
	public static final String MYPATH = "data/data/com.shawnkrecker.eventation/databases/";
	
	TextView groupNameView;
	TextView descriptionView;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_sign_up);
		
		groupNameView = (TextView) findViewById(R.id.textView1);
		descriptionView = (TextView) findViewById(R.id.textView4);
		button = (Button) findViewById(R.id.button1);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		
		
		groupNameView.setText(bundle.getString("groupName"));
		descriptionView.setText(bundle.getString("groupDescription"));
		
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				
				SQLiteDatabase db = null;
				
				MyDBHelper helper = null;
				
				if(signUp(groupNameView.getText().toString())){
					
					db = null;
					
					helper = null;
					
					ContentValues values = null;
					
					try{
						helper = new MyDBHelper(view.getContext());
						
						db = helper.getWritableDatabase();
			
						values = new ContentValues();
						values.put(COLUMN_GROUP_NAME,groupNameView.getText().toString());
						values.put(COLUMN_GROUP_DESCRIPTION,descriptionView.getText().toString());
						db.insert(SUBSCRIBED_GROUPS_TABLENAME,null,values);		
						
								
					}catch(SQLiteCantOpenDatabaseException ex){
						
					}
					
					if(helper != null) helper.close();
					if(db != null) db.close(); 
					
					
					Intent intent = new Intent(view.getContext(), MainActivity.class);
					startActivity(intent);
					
					finish();
				}else{
					
				}
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_sign_up, menu);
		return true;
	}
	
	public Boolean signUp(String groupName){
		Boolean group = false;
		try{
			group = new AsyncTask<String, Void, Boolean>(){
				
				public Boolean doInBackground(String... s){

					String userName = null;

					Boolean result = null;
					
					try{
						
						InputStream inputStream = openFileInput("user_info");
						
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
						
						String readerIn = bufferedReader.readLine();
						
						String[] info = readerIn.split(" ");
						
						userName = info[0];
									
						bufferedReader.close();
						
					} catch(FileNotFoundException ex1){
						Log.d("eventation", "GroupSignUp: FileNotFoundException");
						
					}catch(IOException ex2){
						Log.d("eventation", "GroupSignUp: IOException");
					}
					
					String groupName = s[0];
					
					Groupsubscribersendpoint.Builder builder = new Groupsubscribersendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
					Groupsubscribersendpoint gEnd = builder.build();
					
					GroupSubscribers groupSub = new GroupSubscribers();
					
					groupSub.setId(new Random().nextLong());
					groupSub.setGroupID(groupName);
					groupSub.setUserID(userName);
					
					
					SharedPreferences prefs = getGCMPreferences();
					String regID = prefs.getString(PROPERTY_REG_ID, "error");
					
					if(regID.equals("error")) return false;
					
					groupSub.setRegID(regID);
					
					
					
					try{
						gEnd.insertGroupSubscribers(groupSub).execute();
						return true;
					}catch(IOException ex){
						Log.d("eventation", "GroupSignUp: IOException" + ex.getStackTrace().toString());
						
						return false;
					}
							
				}
				
				private SharedPreferences getGCMPreferences(){
					
				    return getSharedPreferences(StartActivity.class.getSimpleName(),
				            Context.MODE_PRIVATE);
				}
				
				
			}.execute(groupName).get();
			
			
			
			
		}catch(ExecutionException ex){
			Log.d("eventation", "GroupSignUp: ExecutionException");
			return false;
		}catch(InterruptedException ex2){
			Log.d("eventation", "GroupSignUp: InterruptedException");
			return false;
		}
		
		//if(group == false)
		
		
		
		return group;
	}

}
