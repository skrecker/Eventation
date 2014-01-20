package com.shawnkrecker.eventation;

//import com.google.api.server.spi.response.CollectionResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.shawnkrecker.eventation.subscriberendpoint.Subscriberendpoint;
import com.shawnkrecker.eventation.subscriberendpoint.model.Subscriber;

import android.os.AsyncTask;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SignInActivity extends Activity {
	
	public static final String PREF_NAME = "prefs";
	
	ProgressBar progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		
		Button button = (Button) findViewById(R.id.button1);
		progress = (ProgressBar) findViewById(R.id.progressBar1);
		
		try{
			String value = getIntent().getExtras().getString("signInFailed");		
			TextView tView = (TextView) findViewById(R.id.userNameEdit);
			tView.setText("Username/password incorrect");
			tView.setHint(value);
			
		}catch(NullPointerException ex){
			//Do Nothing
		}
			
		button.setOnClickListener(new OnClickListener(){
			
			public void onClick(View view){
			
				/*
				ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
				bar.setVisibility(View.VISIBLE);
				*/
				signIn(view);
				
			}
			
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		return true;
	}
	
	public void signIn(View view){
		
		Intent intent = null;
		Bundle bundle = new Bundle();
		EditText userNameEdit = (EditText) findViewById(R.id.userNameEdit);
		EditText passwordEdit = (EditText) findViewById(R.id.passwordEdit);
		
		String userName = userNameEdit.getText().toString();
		String password = passwordEdit.getText().toString();
		
		
		
		Subscriber result = null;
		if(userName.equals("") || userName == null){
			intent = new Intent(this, SignInActivity.class);
			bundle.putString("signInFailed", "signInFailed");
			intent.putExtras(bundle);
			
		
			
		}else{
			bundle.putString("userName", userName);
			bundle.putString("password", password);
			
			
			
			
			
			try{
				SignInTask task = new SignInTask();
				task.execute(bundle);
				result = task.get();				
				
			}catch(ExecutionException exEx){
				result = null;
			}catch(InterruptedException interruptEx){
				result = null;
			}
			
			
		}
		
		if(result == null){
			
			userNameEdit.setText("");
			userNameEdit.setHint("Error");
			passwordEdit.setText("");
			
			
			/*
			intent = new Intent(this, SignInActivity.class);
			bundle = new Bundle();
			bundle.putString("signInFailed", "signInFailed");
			*/
		}else{
			intent = new Intent(this, MainActivity.class);
			bundle = new Bundle();
			
			String fileName = "user_info";
			String s = userName+ " " + result.getFirstName() + " " +  result.getLastName() + " " + result.getEmail();
			
			try{
				FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
				fos.write(s.getBytes());
				fos.close();
				
			}catch(FileNotFoundException ex1){
				Log.d("Eventation", "FileNotFoundException");
			}catch(IOException ex2){
				Log.d("Eventation", "IOException");
			}
			
			
			
			
			startActivity(intent);
			finish();
			
		}
		
		
		
		
	}
	
	private class SignInTask extends AsyncTask<Bundle,ProgressBar, Subscriber>{		
		
		@Override
		public Subscriber doInBackground(Bundle... b){
			
			
			Bundle bundle = b[0];
			
			String userName = bundle.getString("userName");
			String password = bundle.getString("password");
			
			try{

				Subscriberendpoint.Builder builder = new Subscriberendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
				Subscriberendpoint sub = builder.build();
					
				Subscriber subscriber = sub.getSubscriber(userName).execute();
				
				try{
				
					if(subscriber.getPassword().equals(password)){		
						
						return subscriber;
					}else{
						return null;
					}
				
				}catch(NullPointerException nullEx){
					return null;
				}
				
							
			} catch(IOException e){
				return null;
			}
			
			
			
		}	
		
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			progress.setVisibility(View.VISIBLE);
			Log.d("Progress Update","Failed");
		}
		
//		
//		@Override
//		protected void onProgressUpdate(ProgressBar... p){
//			super.onProgressUpdate();
//			pBar.setVisibility(View.VISIBLE);
//		}
		
		protected void onPostExecute(){
//			progress.setVisibility(ProgressBar.INVISIBLE);
		}
		
	}

}
