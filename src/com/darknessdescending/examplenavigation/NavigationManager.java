package com.darknessdescending.examplenavigation;

import java.util.*;
import android.util.*;

public class NavigationManager
{
	static final String TAG = "NavigationManager";
	
	private List<NavigationStep> listOfSteps = new ArrayList<NavigationStep>();
	private Map<NavigationStep, List<NavigationStep>> mapOfSteps = new HashMap<NavigationStep, List<NavigationStep>>();
	private ListIterator mListIterator;
	private NavigationStep mNavigationStep;
	private NavigationStep topNavigationStep;
	
	public NavigationManager() {
		topNavigationStep = new NavigationStep();
		mapOfSteps.put(topNavigationStep, listOfSteps);
	}
	
	public void Initialize() {
		listOfSteps = mapOfSteps.get(topNavigationStep);
		mNavigationStep = listOfSteps.get(0);
		if(mNavigationStep != null) {
			Log.i(TAG, "mNavigationStep contains a value: " + (String) mNavigationStep.getStep());
		} else {
			Log.i(TAG, "mNavigationStep is null");
		}   
		//TODO this is potentially broken.
		//TODO set iterator to location 0
	}
	
	public void addStep(NavigationStep step) {
		//
		//  Add a step to the top level navagation structure
		//    The parent step will be = topNavigationStep
		//
		NavigationStep parent;
		if(step.getParentStep() != null) {
			parent = step.getParentStep();
		} else {
			parent = topNavigationStep;
			step.setParentStep(parent);
		}
		if(mapOfSteps.containsKey(parent)) {
			listOfSteps = mapOfSteps.get(parent);
		} else {
			listOfSteps = new ArrayList<NavigationStep>();
			mapOfSteps.put(parent, listOfSteps);
		}
		listOfSteps.add(step);
	}
	
	public NavigationStep getStep() {
		return mNavigationStep;
	}
	
	public boolean NavigateForward() {
		Log.i(TAG, "mapOfSteps: " + mapOfSteps.toString());
		if(mapOfSteps.containsKey(mNavigationStep)) {
			listOfSteps = mapOfSteps.get(mNavigationStep);
			mListIterator = listOfSteps.listIterator();
			mNavigationStep = listOfSteps.get(0);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean NavigateBack() {
		if(mNavigationStep.getParentStep() != null) {
			mNavigationStep = mNavigationStep.getParentStep();
			listOfSteps = mapOfSteps.get(mNavigationStep.getParentStep());
			mListIterator = listOfSteps.listIterator();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean NavigateLeft() {
		mListIterator = listOfSteps.listIterator();
		if(mListIterator.hasPrevious()) {
			mNavigationStep = (NavigationStep) mListIterator.previous();
			return true;
		} else {
			return false;
		}
	}
	
	public boolean NavigateRight() {
		mListIterator = listOfSteps.listIterator();
		if(mListIterator.hasNext()) {
			mNavigationStep = (NavigationStep) mListIterator.next();
			return true;
		} else {
			return false;
		}
	}
	
}
