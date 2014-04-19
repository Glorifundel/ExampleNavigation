package com.darknessdescending.examplenavigation;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
{
	
	TextView mTextView;
	NavigationManager mNavigationManager;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		mNavigationManager = new NavigationManager();
		
		mTextView = (TextView) findViewById(R.id.textMain);
		mTextView.setText("Updated");
    }
}
