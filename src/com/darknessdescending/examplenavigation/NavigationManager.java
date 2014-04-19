package com.darknessdescending.examplenavigation;

import java.util.*;

public class NavigationManager
{
	List<NavigationStep> listOfSteps = new ArrayList<NavigationStep>();
	List<NavigationStep> listOfCurrentSteps = new ArrayList<NavigationStep>();
	ListIterator mListIterator;
	NavigationStep mNavigationStep;
	
	public NavigationManager() {
		
	}
	
	private boolean CompileListOfCurrentSteps(NavigationStep parent) {
		listOfCurrentSteps.clear();
		mListIterator = (ListIterator) listOfSteps.listIterator(listOfSteps.size());
		while (mListIterator.hasNext()) {
			mNavigationStep = (NavigationStep) mListIterator.next();
			if( parent == mNavigationStep.getParentStep() ) {
				listOfCurrentSteps.add(mNavigationStep);
			}
		}
	}
	
	public void Initialize() {
		mListIterator = (ListIterator) listOfSteps.iterator();
		//TODO set iterator to location 0
		CompileListOfCurrentSteps(null);
	}
	
	public NavigationStep addStep(Object step) {
		mNavigationStep = new NavigationStep(step);
		listOfSteps.add(mNavigationStep);
		return mNavigationStep;
	}
	
	public NavigationStep addChildStep(NavigationStep parent, Object step) {
		mNavigationStep = new NavigationStep(step);
		mNavigationStep.setParentStep(parent);
		listOfSteps.add(mNavigationStep);
		return mNavigationStep;
	}
	
	public boolean NavigateForward() {
		mListIterator = (ListIterator) listOfCurrentSteps.iterator(listOfCurrentSteps.size());
		CompileListOfCurrentSteps(mListIterator.
		return true;
	}
	
	public boolean NavigateBack() {
		return true;
	}
	
	public boolean NavigateLeft() {
		return true;
	}
	
	public boolean NavigateRight() {
		return true;
	}
	
	
}
