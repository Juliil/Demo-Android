package com.itm.mobile.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Session {
	
	private static final String IDIOMA = "CONDO";
	private SharedPreferences share;
	private SharedPreferences.Editor editor;
	
	public Session(Context context){
		share  = context.getSharedPreferences(IDIOMA,0);
		editor = share.edit();		
	}
	
	public void setKey(String key ,String value){
		editor.putString(key, value);
	    editor.commit();
	}
	
	public String getKey(String key,String valueDefault){
		return  share.getString(key, valueDefault);
	}
		
	public void setKeyInt(String key ,int value){
		editor.putInt(key, value);
	    editor.commit();
	}
	
	public int getKeyInt(String key, int valueDefault){
		return  share.getInt(key, valueDefault);
	}

	public void setKeyArrayList(String key ,ArrayList<?> value){
		Gson gson = new Gson();
		String json = gson.toJson(value);
		editor.putString(key, json);
		editor.commit();
	}

	public void setKeyObj(String key ,Object value){
		Gson gson = new Gson();
		String json = gson.toJson(value);
		editor.putString(key, json);
		editor.commit();
	}
	
	public void deleteKey(){
		editor.clear();
		editor.commit();
	}
	
}
