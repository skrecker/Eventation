package com.shawnkrecker.eventation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SignInSignUp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sing_in_sign_up);
		
		Button signInButton = (Button) findViewById(R.id.button1);
		
		Button signUpButton = (Button) findViewById(R.id.button2);
		
		OnClickListener listener = new OnClickListener(){
			public void onClick(View view){
				buttonClick(view);
				
			}
		};
		
		signInButton.setOnClickListener(listener);
		signUpButton.setOnClickListener(listener);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sing_in_sign_up, menu);
		return true;
	}
	
	public void buttonClick(View view){
		
		Intent intent = null;
		
		
		if(view == findViewById(R.id.button1)){
			intent = new Intent(this, SignInActivity.class);
			
			
		}else if(view == findViewById(R.id.button2)){
			intent = new Intent(this, SignUpActivity.class);
			
		}
		
		startActivity(intent);
		finish();
	}

}
