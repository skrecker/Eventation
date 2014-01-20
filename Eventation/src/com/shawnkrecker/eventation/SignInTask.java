package com.shawnkrecker.eventation;

import java.io.IOException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.shawnkrecker.eventation.subscriberendpoint.Subscriberendpoint;
import com.shawnkrecker.eventation.subscriberendpoint.model.Subscriber;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

public class SignInTask extends AsyncTask<Bundle,Void, Subscriber>{
	
	
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
	
}
