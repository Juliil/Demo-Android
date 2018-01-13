package com.itm.mobile.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

public class UserSessionManager {
	
	// Shared Preferences reference
	SharedPreferences pref;
	
	// Editor reference for Shared preferences
	Editor editor;
	
	// Context
	Context _context;
	
	// Shared pref mode
	int PRIVATE_MODE = 0;
	
	// Sharedpref file name
	private static final String PREFER_NAME = "CONDO";
	
	// All Shared Preferences Keys
	private static final String IS_USER_LOGIN = "IsUserLoggedIn";

	// Email address (make variable public to access from outside)
	public static final String KEY_ID = "id";

	// User name (make variable public to access from outside)
	public static final String KEY_PESSOA_ID = "usuario_id";
	
	// User name (make variable public to access from outside)
	public static final String KEY_NAME = "name";
	
	// Email address (make variable public to access from outside)
	public static final String KEY_EMAIL = "email";

	// Email address (make variable public to access from outside)
	public static final String KEY_UNIDADE_ID = "unidade_id";

	// Email address (make variable public to access from outside)
	public static final String KEY_UNIDADE_DESCR = "unidade_descr";
	
	// Constructor
	public UserSessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	
	//Create login session
	public void createUserLoginSession(String id, String pessoa_id, String name, String email, String unidade_id, String unidade_descr){
		// Storing login value as TRUE
		editor.putBoolean(IS_USER_LOGIN, true);
		
		// Storing name in pref
		editor.putString(KEY_ID, id);

		// Storing name in pref
		editor.putString(KEY_PESSOA_ID, pessoa_id);

		// Storing name in pref
		editor.putString(KEY_NAME, name);
		
		// Storing email in pref
		editor.putString(KEY_EMAIL, email);

		// Storing unidade in pref
		editor.putString(KEY_UNIDADE_ID, unidade_id);

		// Storing email in pref
		editor.putString(KEY_UNIDADE_DESCR, unidade_descr);
		
		// commit changes
		editor.commit();
	}	
	
	/**
	 * Check login method will check user login status
	 * If false it will redirect user to login page
	 * Else do anything
	 * */
	@SuppressWarnings("StatementWithEmptyBody")
	public boolean checkLogin(){
		/* Check login status */
		if(!this.isUserLoggedIn()){
			
			/*// user is not logged in redirect him to Login Activity
			Intent i = new Intent(_context, LoginActivity.class);
			
			// Closing all the Activities from stack
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			// Staring Login Activity
			_context.startActivity(i);
			
			return true;*/
		}
		return false;
	}
	
	
	
	/**
	 * Get stored session data
	 * */
	public HashMap<String, String> getUserDetails(){
		
		//Use hashmap to store user credentials
		HashMap<String, String> user = new HashMap<String, String>();

		// user name
		user.put(KEY_ID, pref.getString(KEY_ID,null));

		// Storing name in pref
		user.put(KEY_PESSOA_ID, pref.getString(KEY_PESSOA_ID, null));

		// user name
		user.put(KEY_NAME, pref.getString(KEY_NAME, null));
		
		// user email id
		user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

		// user unidade id
		user.put(KEY_UNIDADE_ID, pref.getString(KEY_UNIDADE_ID, null));

		// user email id
		user.put(KEY_UNIDADE_DESCR, pref.getString(KEY_UNIDADE_DESCR, null));
		
		// return user
		return user;
	}
	
	/**
	 * Clear session details
	 * */
	public void logoutUser(){
		
		/*// Clearing all user data from Shared Preferences
		editor.clear();
		editor.commit();
		
		// After logout redirect user to Login Activity
		Intent i = new Intent(_context, LoginActivity.class);
		
		// Closing all the Activities
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		// Add new Flag to start new Activity
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		// Staring Login Activity
		_context.startActivity(i);*/
	}
	
	
	// Check for login
	public boolean isUserLoggedIn(){
		return pref.getBoolean(IS_USER_LOGIN, false);
	}
}
