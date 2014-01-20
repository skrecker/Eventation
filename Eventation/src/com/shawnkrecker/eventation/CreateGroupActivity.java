package com.shawnkrecker.eventation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.shawnkrecker.eventation.groupendpoint.Groupendpoint;
import com.shawnkrecker.eventation.groupendpoint.Groupendpoint.InsertGroup;
import com.shawnkrecker.eventation.groupendpoint.model.Group;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.*;

public class CreateGroupActivity extends Activity {

	Button submitButton;
	public static final String DATABASE_NAME = "eventation.db";
	
	//public static final String COLUMN_ID = "_id";
	
	public static final String COLUMN_GROUP_NAME = "_id";
	
	private static final String COLUMN_GROUP_DESCRIPTION = "group_description";
	
	public static final String TABLE_NAME = "mygroups";
	
	public static final String MYPATH = "data/data/com.shawnkrecker.eventation/databases/";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_group);
		
		submitButton = (Button) findViewById(R.id.button1);
		submitButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
					createGroup();
			}
		});
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_group, menu);
		return true;
	}

	
	public void createGroup(){
		
		String adminName = null;
		
		try{
			
			InputStream inputStream = openFileInput("user_info");
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			String readerIn = bufferedReader.readLine();
			
			String[] info = readerIn.split(" ");
			
			adminName = info[0];
						
			bufferedReader.close();
			
		} catch(FileNotFoundException ex1){
			Log.d("eventation", "StartActivity: FileNotFoundException");
			
		}catch(IOException ex2){
			Log.d("eventation", "StartActivity: IOException");
		}
		
		String groupName;
		String groupDescription;
		
		EditText groupNameView = (EditText) findViewById(R.id.editText1);
		groupName = groupNameView.getText().toString();
		
		EditText groupDescriptionView = (EditText) findViewById(R.id.editText2);
		groupDescription = groupDescriptionView.getText().toString();
		
		Bundle bundle = new Bundle();
		bundle.putString("adminName", adminName);	
		bundle.putString("groupName",groupName);
		bundle.putString("groupDescription", groupDescription);
		
		Group result = null;
		
		SQLiteDatabase db = null;
		
		MyDBHelper helper = null;
		
		ContentValues values = null;
		
		
		try{
			result = new CreateGroupTask().execute(bundle).get();
			
			
		}catch(ExecutionException ee){
			result = null;
			Log.d("ExecutionException","ExecutionException");
		}catch(InterruptedException iE){
			Log.d("InterruptedException","InterruptedException");
			result = null;
		}
		
		if(result != null){
			
			try{
				helper = new MyDBHelper(getApplicationContext());
				
				db = helper.getWritableDatabase();
	
				values = new ContentValues();
				values.put(COLUMN_GROUP_NAME,result.getGroupName());
				values.put(COLUMN_GROUP_DESCRIPTION,result.getGroupDescription());
				db.insert(TABLE_NAME,null,values);		
				
						
			}catch(SQLiteCantOpenDatabaseException ex){
				
			}
			
			if(helper != null){
				helper.close();
			}
			if(db != null){
				db.close();
			}
		
			
			finish();
		}else{
			groupNameView.setText("");
			groupNameView.setHint("Group Already Exists");
		}
		
		
	
	}
	
	
	private class CreateGroupTask extends AsyncTask<Bundle,Void,Group>{
		
		
		public Group doInBackground(Bundle... b){
			Bundle bundle = b[0];
			
			String adminName = bundle.getString("adminName");
			String groupName = bundle.getString("groupName");
			String groupDescription = bundle.getString("groupDescription");
			
			try{
				
				Group group = new Group();
				group.setAdminName(adminName);
				group.setGroupName(groupName);
				group.setGroupDescription(groupDescription);
				group.setNumSubscribers(0);
				
				Groupendpoint.Builder builder = new Groupendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
				Groupendpoint gEnd = builder.build();
				
			   return gEnd.insertGroup(group).execute();    
			   
				
			}catch(IOException e){
				Log.d("IOException in CreateGroupTask","IOException in CreateGroupTask");
				Log.d("e", e.toString());
				return null;
			}

		
		}

	
		
	}
	
}
