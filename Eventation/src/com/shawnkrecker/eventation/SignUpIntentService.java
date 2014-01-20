package com.shawnkrecker.eventation;

import java.io.IOException;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;
import com.shawnkrecker.eventation.subscriberendpoint.Subscriberendpoint;
import com.shawnkrecker.eventation.subscriberendpoint.model.Subscriber;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SignUpIntentService extends IntentService {
	// TODO: Rename actions, choose action names that describe tasks that this
	// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
	private static final String ACTION_FOO = "com.shawnkrecker.eventation.action.FOO";
	private static final String ACTION_BAZ = "com.shawnkrecker.eventation.action.BAZ";

	// TODO: Rename parameters
	private static final String USERNAME = "user_name";
	private static final String PASSWORD = "password";

	/**
	 * Starts this service to perform action Foo with the given parameters. If
	 * the service is already performing a task this action will be queued.
	 * 
	 * @see IntentService
	 */
	// TODO: Customize helper method
	public static void startActionFoo(Context context, String param1,
			String param2) {
		Intent intent = new Intent(context, SignUpIntentService.class);
		intent.setAction(ACTION_FOO);
		intent.putExtra(USERNAME, param1);
		intent.putExtra(PASSWORD, param2);
		context.startService(intent);
	}

	/**
	 * Starts this service to perform action Baz with the given parameters. If
	 * the service is already performing a task this action will be queued.
	 * 
	 * @see IntentService
	 */
	// TODO: Customize helper method
	public static void startActionBaz(Context context, String param1,
			String param2) {
		Intent intent = new Intent(context, SignUpIntentService.class);
		intent.setAction(ACTION_BAZ);
		intent.putExtra(USERNAME, param1);
		intent.putExtra(PASSWORD, param2);
		context.startService(intent);
	}

	public SignUpIntentService() {
		super("SignUpIntentService");

	
		
		
		
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		if (intent != null) {
			final String action = intent.getAction();
			if (ACTION_FOO.equals(action)) {
				final String param1 = intent.getStringExtra(USERNAME);
				final String param2 = intent.getStringExtra(PASSWORD);
				handleActionFoo(param1, param2);
			} else if (ACTION_BAZ.equals(action)) {
				final String param1 = intent.getStringExtra(USERNAME);
				final String param2 = intent.getStringExtra(PASSWORD);
				handleActionBaz(param1, param2);
			}
		}
	}

	/**
	 * Handle action Foo in the provided background thread with the provided
	 * parameters.
	 */
	private void handleActionFoo(String param1, String param2) {
		// TODO: Handle action Foo
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Handle action Baz in the provided background thread with the provided
	 * parameters.
	 */
	private void handleActionBaz(String param1, String param2) {
		// TODO: Handle action Baz
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
