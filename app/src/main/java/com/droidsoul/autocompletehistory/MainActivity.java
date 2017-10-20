package com.droidsoul.autocompletehistory;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "PingBusPrefs";
    public static final String PREFS_SEARCH_HISTORY = "SearchHistory";
    private SharedPreferences settings;
    private Set history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
