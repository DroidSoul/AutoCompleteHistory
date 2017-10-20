package com.droidsoul.autocompletehistory;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "PingBusPrefs";
    public static final String PREFS_SEARCH_HISTORY = "SearchHistory";
    private SharedPreferences settings;
    private Set history;
    AutoCompleteTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences(PREFS_NAME, 0);
        history = settings.getStringSet(PREFS_SEARCH_HISTORY, new HashSet());
        textView = (AutoCompleteTextView) findViewById(R.id.etInput);
        setAutoCompleteSource();
        textView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                 addSearchInput(textView.getText().toString());
                 return true;
                }
                 return false; }
                });

    }

    private void setAutoCompleteSource() {
//        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.etInput);
        ArrayAdapter adapter = new ArrayAdapter( this, android.R.layout.simple_list_item_1, history.toArray(new String[history.size()]));
        textView.setAdapter(adapter);
    }

    private void addSearchInput(String input) {
        if (!history.contains(input)) {
            history.add(input);
            setAutoCompleteSource();
        }
    }

    private void savePrefs() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(PREFS_SEARCH_HISTORY, history);
        editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePrefs();
    }

}
