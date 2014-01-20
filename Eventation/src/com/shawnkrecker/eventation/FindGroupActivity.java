package com.shawnkrecker.eventation;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.shawnkrecker.eventation.groupendpoint.Groupendpoint;
import com.shawnkrecker.eventation.groupendpoint.model.Group;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FindGroupActivity extends Activity {
	
	EditText editText;
	Button button;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_group);
		
		editText = (EditText) findViewById(R.id.editText1);
		button = (Button) findViewById(R.id.button1);
		
		button.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				Bundle bundle = new Bundle();
				String groupName = editText.getText().toString();
				
				bundle.putString("groupName",groupName);
				Group group = findGroup(bundle);
				
				if(group != null){
					Intent intent = new Intent(view.getContext(), GroupSignUpActivity.class);
					String groupDescription = group.getGroupDescription();
					Bundle groupBundle = new Bundle();
					groupBundle.putString("groupName", groupName);
					groupBundle.putString("groupDescription", groupDescription);
					intent.putExtras(groupBundle);
					startActivity(intent);
					
					
				}else{
					editText.setText("group not found");
				};
				
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_group, menu);
		return true;
	}
	
	public Group findGroup(Bundle bundle){
		
		Group group = null;
		try{
			group = new AsyncTask<Bundle, Void, Group>(){
				
				public Group doInBackground(Bundle... b){
					
					Bundle bundle = b[0];
					
					String groupName = bundle.getString("groupName");
					
					Groupendpoint.Builder builder = new Groupendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
					Groupendpoint gEnd = builder.build();
					
					Group group = null;
					
					try{
						group = gEnd.getGroup(groupName).execute();
					}catch(IOException ex){
						return null;
					}
					
					return group;
					
				}
			}.execute(bundle).get();
		}catch(ExecutionException ex){
			return null;
		}catch(InterruptedException ex2){
			return null;
		}
		
		return group;
	}
	
	

}
