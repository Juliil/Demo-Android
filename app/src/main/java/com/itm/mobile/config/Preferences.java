package com.itm.mobile.config;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.preference.PreferenceManager;


import com.itm.mobile.petlover.R;


public class Preferences extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(Preferences.this, R.xml.pref_general, false);

    }
}
