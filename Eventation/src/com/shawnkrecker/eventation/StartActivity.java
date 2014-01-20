package com.shawnkrecker.eventation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.shawnkrecker.eventation.deviceinfoendpoint.Deviceinfoendpoint;
import com.shawnkrecker.eventation.deviceinfoendpoint.model.DeviceInfo;
import com.shawnkrecker.eventation.eventendpoint.Eventendpoint;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

//import com.google.android.gms.gcm.GoogleCloudMessaging;

public class StartActivity extends Activity {
	
	public static final String PREF_NAME = "prefs";
	
	
	public static final String EXTRA_MESSAGE = "message";
	public static final String PROPERTY_REG_ID = "registration_id";
	private static final String PROPERTY_APP_VERSION = "appVersion";
	
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	
	String SENDER_ID = "1089823764628";
	
	static final String TAG = "GCMSignUp";
	
	GoogleCloudMessaging gcm;
	Context context;
	SharedPreferences prefs;
	AtomicInteger msgID = new AtomicInteger();
	
	String regid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		context = getApplicationContext();
		
		if(checkPlayServices()){
			
			gcm = GoogleCloudMessaging.getInstance(this);
			regid = getRegistrationID(context);
			if(regid.isEmpty()){
				registerInBackground();
			}
			
			
		}else{
			 Log.i(TAG, "No valid Google Play Services APK found.");
		}
		
		new Handler().postDelayed(new Runnable(){
			public void run(){
				
			
				Intent intent = null;
				
				String info = null;
				
				try{
					
					InputStream inputStream = openFileInput("user_info");
					
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
					
					info = bufferedReader.readLine();
					
					bufferedReader.close();
					
				} catch(FileNotFoundException ex1){
					Log.d("eventation", "StartActivity: FileNotFoundException");
					
				}catch(IOException ex2){
					Log.d("eventation", "StartActivity: IOException");
				}
				
		
				if(info == null || info.equals("")){
					intent = new Intent(StartActivity.this,SignInSignUp.class);
				}else{
					intent = new Intent(StartActivity.this, MainActivity.class);
				}
				
				startActivity(intent);
				finish();
			
			}
			
			
		},5000);
	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
	}
	
	private boolean checkPlayServices(){
		
		  int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		    if (resultCode != ConnectionResult.SUCCESS) {
		        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
		            GooglePlayServicesUtil.getErrorDialog(resultCode, this,
		                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
		        } else {
		            Log.i(TAG, "This device is not supported.");
		            finish();
		        }
		        return false;
		    }
		    return true;
		
	}
	
	private String getRegistrationID(Context context){
		final SharedPreferences prefs = getGCMPreferences(context);
		String registrationId = prefs.getString(PROPERTY_REG_ID, "");
		
		if(registrationId.isEmpty()){
			Log.i(TAG, "Registration not found.");
	        return "";
		}
		
		int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	    int currentVersion = getAppVersion(context);
	    if (registeredVersion != currentVersion) {
	        Log.i(TAG, "App version changed.");
	        return "";
	    }
	    return registrationId;
	}
	
	private SharedPreferences getGCMPreferences(Context context){
		
	    return getSharedPreferences(StartActivity.class.getSimpleName(),
	            Context.MODE_PRIVATE);
	}
	
	private static int getAppVersion(Context context){
		  try {
		     PackageInfo packageInfo = context.getPackageManager()
		     .getPackageInfo(context.getPackageName(), 0);
		     return packageInfo.versionCode;
		   } catch (NameNotFoundException e) {
		    // should never happen
		    throw new RuntimeException("Could not get package name: " + e);
		   }
	}
	
	private void registerInBackground() {
	    new AsyncTask<Void,Void,String>() {
	        protected String doInBackground(Void... params) {
	            String msg = "";
	            try {
	                if (gcm == null) {
	                    gcm = GoogleCloudMessaging.getInstance(context);
	                }
	                regid = gcm.register(SENDER_ID);
	                msg = "Device registered, registration ID=" + regid;

	                // You should send the registration ID to your server over HTTP,
	                // so it can use GCM/HTTP or CCS to send messages to your app.
	                // The request to your server should be authenticated if your app
	                // is using accounts.
	                sendRegistrationIdToBackend(regid);

	                // For this demo: we don't need to send it because the device
	                // will send upstream messages to a server that echo back the
	                // message using the 'from' address in the message.

	                // Persist the regID - no need to register again.
	                storeRegistrationId(context, regid);
	            } catch (IOException ex) {
	                msg = "Error :" + ex.getMessage();
	                // If there is an error, don't just keep trying to register.
	                // Require the user to click a button again, or perform
	                // exponential back-off.
	            }
	            return msg;
	        }

	        protected void onPostExecute(String msg) {
	           // mDisplay.append(msg + "\n");
	        }
	    }.execute(null, null, null);
	    	//...
	}
	
	/**
	 * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP
	 * or CCS to send messages to your app. Not needed for this demo since the
	 * device sends upstream messages to a server that echoes back the message
	 * using the 'from' address in the message.
	 */
	private void sendRegistrationIdToBackend(String regId) {
	    // Your implementation here.
		DeviceInfo info = new DeviceInfo();
		info.setDeviceRegistrationID(regId);
		
		info.setTimestamp(System.currentTimeMillis());
		
		try{
			Deviceinfoendpoint.Builder builder = new Deviceinfoendpoint.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
			Deviceinfoendpoint endpoint = builder.build();
			
			endpoint.insertDeviceInfo(info).execute();
			
		}catch(com.google.api.client.googleapis.json.GoogleJsonResponseException ex1){
			Log.e("failed", ex1.toString());
		
		}catch(IOException ex){
			Log.e("failed", ex.toString());
		}
		
	}

	/**
	 * Stores the registration ID and app versionCode in the application's
	 * {@code SharedPreferences}.
	 *
	 * @param context application's context.
	 * @param regId registration ID
	 */
	private void storeRegistrationId(Context context, String regId) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    int appVersion = getAppVersion(context);
	    Log.i(TAG, "Saving regId on app version " + appVersion);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(PROPERTY_REG_ID, regId);
	    editor.putInt(PROPERTY_APP_VERSION, appVersion);
	    editor.commit();
	}
	
}
