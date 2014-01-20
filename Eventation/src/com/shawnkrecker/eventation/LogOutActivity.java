package com.shawnkrecker.eventation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;

public class LogOutActivity extends Activity {
	
	public static final String PREF_NAME = "prefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try{
			FileOutputStream fos = openFileOutput("user_info", Context.MODE_PRIVATE);
			String clear = "";
			fos.write(clear.getBytes());
			fos.close();
			
		}catch(FileNotFoundException ex1){
			Log.d("Eventation", "FileNotFoundException");
		}catch(IOException ex2){
			Log.d("Eventation", "IOException");
		}
		

		Intent intent = new Intent(this, SignInSignUp.class);
		startActivity(intent);
		finish();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_out, menu);
		return true;
	}

}
