package com.darknessdescending.examplenavigation;

import android.app.Activity;
import android.content.Context;
import android.gesture.Gesture;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.TextView;

public class MainActivity extends Activity
{
	static final String TAG = "MainActivity";
	public final int FLING_SIGNIFICANT_DISTANCE = 120;
	
	TextView mTextView;
	NavigationManager mNavigationManager;
	NavigationStep mNavigationStep;
	NavigationStep tNavigationStep;
	String StepObject = new String();
	String TextViewOutput = new String();
	boolean truefalse;
	
	private GestureDetector mGestureDetector;
	private ViewGroup mViewGroup;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		mNavigationManager = new NavigationManager();
		NavigationManagerTest();
		
		TextViewOutput = (String) mNavigationManager.getStep().getStep();
		mTextView = (TextView) findViewById(R.id.textMain);
		mTextView.setText(TextViewOutput);
		mTextView.invalidate();
		
		mViewGroup = (ViewGroup) findViewById(R.id.mainLayout);
		mViewGroup.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (mGestureDetector.onTouchEvent(event)) {
	                return true;
	            }
				return false;
			}
		});
		
		mGestureDetector = new GestureDetector(new SimpleOnGestureListener(){
    		@Override
            public boolean onDoubleTap(MotionEvent event) {
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
            	boolean temp = false;
            	temp = mNavigationManager.NavigateForward();
    			Log.d(TAG,"SingleTap, NavigateForward(): " + temp);
    			TextViewOutput = (String) mNavigationManager.getStep().getStep();
            	mTextView.setText(TextViewOutput);
        		mTextView.invalidate();
            	return true;
            }

            @Override
            public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX, float distanceY) {
                return true;
            }
            
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            	boolean temp = false;
            	if(Math.abs(velocityX) > Math.abs(velocityY)) {
            		//Moving Left or Right
            		//Log.d(TAG,"VelocityX: " + String.valueOf(velocityX));
            		if(velocityX >= 0) {
            			temp = mNavigationManager.NavigateRight();
            			Log.d(TAG,"NavigateRight(): " + temp);
            		} else {
            			temp = mNavigationManager.NavigateLeft();
            			Log.d(TAG,"NavigateLeft(): " + temp);
            		}
            	} else {
            		//Moving Down or Up
            		//Log.d(TAG,"VelocityY: " + String.valueOf(velocityY));
            		if(velocityY >= 0) {
            			//DownFling
            			temp = mNavigationManager.NavigateBack();
            			Log.d(TAG,"DownFling, NavigateBack(): " + temp);
            		} 
            	}
            	TextViewOutput = (String) mNavigationManager.getStep().getStep();
            	mTextView.setText(TextViewOutput);
        		mTextView.invalidate();
            	return temp;
            }

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }
    	});
    }
    
    private void NavigationManagerTest() {
    	NavigationStep stepPatient;
    	NavigationStep stepSummary;
    	NavigationStep stepDetail;
    	mNavigationStep = new NavigationStep();
		StepObject = "Patient 0";
		mNavigationStep.setStep(StepObject);
		mNavigationManager.addStep(mNavigationStep);
		stepPatient = mNavigationStep;
		
		stepSummary = new NavigationStep();
		StepObject = "Patient 0 Summary Medication";
		stepSummary.setStep(StepObject);
		stepSummary.setParentStep(stepPatient);
		mNavigationManager.addStep(stepSummary);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Medication 0";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Medication 1";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Medication 2";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Medication 3";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepSummary = new NavigationStep();
		StepObject = "Patient 0 Summary Allergies";
		stepSummary.setStep(StepObject);
		stepSummary.setParentStep(stepPatient);
		mNavigationManager.addStep(stepSummary);

		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Allergies 0";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Allergies 1";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);

		stepSummary = new NavigationStep();
		StepObject = "Patient 0 Summary Orders";
		stepSummary.setStep(StepObject);
		stepSummary.setParentStep(stepPatient);
		mNavigationManager.addStep(stepSummary);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Orders 0";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Orders 1";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Orders 2";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Orders 3";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Orders 4";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 0 Detail Orders 5";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		//pt 1
		mNavigationStep = new NavigationStep();
		StepObject = "Patient 1";
		mNavigationStep.setStep(StepObject);
		mNavigationManager.addStep(mNavigationStep);
		stepPatient = mNavigationStep;
		
		stepSummary = new NavigationStep();
		StepObject = "Patient 1 Summary Medication";
		stepSummary.setStep(StepObject);
		stepSummary.setParentStep(stepPatient);
		mNavigationManager.addStep(stepSummary);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Medication 0";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Medication 1";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Medication 2";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Medication 3";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepSummary = new NavigationStep();
		StepObject = "Patient 1 Summary Allergies";
		stepSummary.setStep(StepObject);
		stepSummary.setParentStep(stepPatient);
		mNavigationManager.addStep(stepSummary);

		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Allergies 0";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Allergies 1";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);

		stepSummary = new NavigationStep();
		StepObject = "Patient 1 Summary Orders";
		stepSummary.setStep(StepObject);
		stepSummary.setParentStep(stepPatient);
		mNavigationManager.addStep(stepSummary);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Orders 0";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Orders 1";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Orders 2";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Orders 3";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Orders 4";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
		
		stepDetail = new NavigationStep();
		StepObject = "Patient 1 Detail Orders 5";
		stepDetail.setStep(StepObject);
		stepDetail.setParentStep(stepSummary);
		mNavigationManager.addStep(stepDetail);
    }
    
}
