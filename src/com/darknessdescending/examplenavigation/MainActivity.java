package com.darknessdescending.examplenavigation;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.util.*;

public class MainActivity extends Activity
{
	static final String TAG = "MainActivity";
	TextView mTextView;
	NavigationManager mNavigationManager;
	NavigationStep mNavigationStep;
	NavigationStep tNavigationStep;
	String StepObject = new String();
	String TextViewOutput = new String();
	boolean truefalse;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		
		mNavigationManager = new NavigationManager();
		mNavigationStep = new NavigationStep();
		StepObject = "Step0-0";
		mNavigationStep.setStep(StepObject);
		mNavigationManager.addStep(mNavigationStep);
		
		tNavigationStep = new NavigationStep();
		StepObject = "Step1-0";
		tNavigationStep.setStep(StepObject);
		tNavigationStep.setParentStep(mNavigationStep);
		mNavigationManager.addStep(mNavigationStep);
		
		mNavigationManager.Initialize();
		truefalse = mNavigationManager.NavigateForward();
		Log.i(TAG, "mNavigationManager.NavigateForward() = " + String.valueOf(truefalse));
		
		//TextViewOutput = "temp";
		TextViewOutput = (String) mNavigationManager.getStep().getStep();
		Log.i(TAG,"TextViewOutput: " + TextViewOutput);
		
		mTextView = (TextView) findViewById(R.id.textMain);
		mTextView.setText(TextViewOutput);
		mTextView.invalidate();
    }
		
}
