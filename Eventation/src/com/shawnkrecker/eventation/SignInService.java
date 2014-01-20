package com.shawnkrecker.eventation;

import java.io.IOException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.shawnkrecker.eventation.subscriberendpoint.Subscriberendpoint;
import com.shawnkrecker.eventation.subscriberendpoint.model.Subscriber;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

public class SignInService extends Service {
	Subscriberendpoint service;
	
	public SignInService() {
		
		Subscriberendpoint.Builder builder = new Subscriberendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		service = builder.build();
		
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/*private class SignInTask extends AsyncTask<Void, Void, Subscriber>{
		Context context;
		
		public SignInTask(Context context){
			this.context = context;
		}
		
		protected Subscriber doInBackGround(){
			
					
		}
		
	}
	*/
	
	
}
