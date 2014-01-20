package com.shawnkrecker.eventation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.shawnkrecker.eventation.subscriberendpoint.Subscriberendpoint;
import com.shawnkrecker.eventation.subscriberendpoint.Subscriberendpoint.GetSubscriber;
import com.shawnkrecker.eventation.subscriberendpoint.Subscriberendpoint.InsertSubscriber;
import com.shawnkrecker.eventation.subscriberendpoint.model.Subscriber;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends Activity {
	
	public static final String PREF_NAME = "prefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		try{
			Intent intent = getIntent();
			Bundle bundle = intent.getExtras();
			
			String string = bundle.getString("redirect");
			
			if(string.equals("SignUpFailed")){
				
				EditText edit = (EditText) findViewById(R.id.userNameEdit);
				edit.setHint("Username taken");
				edit.requestFocus();
				
				String firstName = bundle.getString("firstName");
				String lastName = bundle.getString("lastName");
				String email = bundle.getString("email");
				
				if(firstName != null){
					EditText firstNameEdit = (EditText) findViewById(R.id.firstNameSignUpEdit);
					firstNameEdit.setText(firstName);
				}
				if(lastName != null){
					EditText lastNameEdit = (EditText) findViewById(R.id.lastNameSignUpEdit);
					lastNameEdit.setText(lastName);
					
				}
				if(email != null){
					EditText emailEdit = (EditText) findViewById(R.id.emailEdit);
					emailEdit.setText(email);
				}
				
				
			}
		
		}catch(NullPointerException ex){
			
		}
		
		
		
		Button signUpButton = (Button) findViewById(R.id.button1);
		
		signUpButton.setOnClickListener(new OnClickListener(){
			public void onClick(View view){
				signUp(view);
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
		return true;
	}
	
	public void signUp(View view){
	
		
					EditText userEditText = (EditText) findViewById(R.id.userNameEdit);
					String userName = userEditText.getText().toString();
					
					EditText firstEditText = (EditText) findViewById(R.id.firstNameSignUpEdit);
					String firstName = firstEditText.getText().toString();
					
					EditText lastEditText = (EditText) findViewById(R.id.lastNameSignUpEdit);
					String lastName = lastEditText.getText().toString();
					
					EditText passwordEditText = (EditText) findViewById(R.id.passwordPass);
					String password = passwordEditText.getText().toString();
					
					EditText emailEditText = (EditText) findViewById(R.id.emailEdit);
					String email = emailEditText.getText().toString();
					
					Bundle bundle = new Bundle();
					bundle.putString("userName", userName);
					bundle.putString("firstName", firstName);
					bundle.putString("lastName", lastName);
					bundle.putString("password", password);
					bundle.putString("email", email);
					
					Boolean result = false;
					try{
						
					
						result = new SignUpTask().execute(bundle).get();
					} catch(ExecutionException exEx){
						result = false;
					}catch(InterruptedException interrupted){
						result = false;
					}
					
					if(result == false){
						redirect(userName, firstName, lastName, email);
					}else{
						
						
						signedUp(userName, firstName, lastName, email);							

					}
					
						

		
	}
	
	private void redirect(String userName, String firstName, String lastName, String email){
		Intent intent = new Intent(this, SignUpActivity.class);
		Bundle bundle = new Bundle();
		
		bundle.putString("redirect", "SignUpFailed");
		bundle.putString("firstName", firstName);
		bundle.putString("lastName", lastName);
		bundle.putString("email", email);
		intent.putExtras(bundle);
		
		startActivity(intent);
		finish();
	}
	
	private void signedUp(String userName, String firstName, String lastName, String email){
		
		Intent intent = new Intent(this, MainActivity.class);
		Bundle bundle = new Bundle();
		
		bundle.putString("activity", "SignUpActivity");
		bundle.putString("userName", userName);
		bundle.putString("firstName", firstName);
		bundle.putString("lastName", lastName);
		bundle.putString("email", email);
		intent.putExtras(bundle);
		
		String s = userName + " " + firstName + " " + lastName + " " + email;
		
		try{
			FileOutputStream fos = openFileOutput("user_info", Context.MODE_PRIVATE);
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
	

private class SignUpTask extends AsyncTask<Bundle,Void,Boolean> {

	
	protected Boolean doInBackground(Bundle... b){
		Bundle bundle = b[0];
		
		String userName = bundle.getString("userName");
		String firstName = bundle.getString("firstName");
		String lastName = bundle.getString("lastName");
		String password = bundle.getString("password");
		String email = bundle.getString("email");
		
		try{
			Subscriber subscriber = new Subscriber();
			Subscriberendpoint.Builder builder = new Subscriberendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
			Subscriberendpoint sub = builder.build();
				
						
			subscriber.setUserName(userName);
			subscriber.setFirstName(firstName);
			subscriber.setLastName(lastName);
			subscriber.setPassword(password);
			subscriber.setEmail(email);	
						
			sub.insertSubscriber(subscriber).execute();
		} catch(IOException e){
			return false;
		}
		
		return true;
	}
	
	protected void onPreExecute(){
		
	}
	
	
	protected void onPostExecute(Boolean result){
		
	}

	
	
}
	
	
	

}
