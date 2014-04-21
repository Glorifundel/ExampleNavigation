package com.darknessdescending.examplenavigation;

import java.util.*;
import android.util.*;

public class NavigationManager
{
	static final String TAG = "NavigationManager";
	private static final boolean LAST_ITERATION_NEXT = true;
	private static final boolean LAST_ITERATION_PREVIOUS = false;
	private static final NavigationStep NAVIGATION_ROOT = new NavigationStep();
	
	private List<NavigationStep> listOfSteps = new ArrayList<NavigationStep>();
	private Map<NavigationStep, List<NavigationStep>> mapOfSteps = new HashMap<NavigationStep, List<NavigationStep>>();
	private ListIterator mListIterator;
	private boolean ListIteratorDirection = LAST_ITERATION_NEXT;
	private NavigationStep mNavigationStep;
	
	public NavigationManager() {
		mapOfSteps.put(NAVIGATION_ROOT, listOfSteps);
		mListIterator = listOfSteps.listIterator();
	}
	
	public void Initialize() {
		listOfSteps = mapOfSteps.get(NAVIGATION_ROOT);
		mListIterator = listOfSteps.listIterator();
		mNavigationStep = (NavigationStep) mListIterator.next();
		ListIteratorDirection = LAST_ITERATION_NEXT;
	}
	
	public void addStep(NavigationStep step) {
		//
		//  Add a step to the top level navagation structure
		//    The parent step will be = topNavigationStep
		//
		if(mNavigationStep == null) {
			mNavigationStep = step;
		}
		NavigationStep parent;
		if(step.getParentStep() != null) {
			parent = step.getParentStep();
		} else {
			parent = NAVIGATION_ROOT;
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
		if(mapOfSteps.containsKey(mNavigationStep)) {
			listOfSteps = mapOfSteps.get(mNavigationStep);
			mListIterator = listOfSteps.listIterator();
			ListIteratorDirection = LAST_ITERATION_NEXT;
			return NavigateRight();
		} else {
			return false;
		}
	}
	
	public boolean NavigateBack() {
		if(mNavigationStep.getParentStep() != null && mNavigationStep.getParentStep() != NAVIGATION_ROOT) {
			mNavigationStep = mNavigationStep.getParentStep();
			listOfSteps = mapOfSteps.get(mNavigationStep.getParentStep());
			mListIterator = listOfSteps.listIterator(listOfSteps.indexOf(mNavigationStep));
			ListIteratorDirection = LAST_ITERATION_NEXT;
			return NavigateRight();
		} else {
			return false;
		}
	}
	
	public boolean NavigateLeft() {
		//
		// If the previous iteration was .previous()
		//
		
		if(ListIteratorDirection == LAST_ITERATION_PREVIOUS) {
			if(mListIterator.hasPrevious()) {
				mNavigationStep = (NavigationStep) mListIterator.previous();
				return true;
			} else {
				return false;
			}
		} else {
			ListIteratorDirection = LAST_ITERATION_PREVIOUS;
			if(mListIterator.hasPrevious()) {
				mListIterator.previous();
				if(mListIterator.hasPrevious()) {
					mNavigationStep = (NavigationStep) mListIterator.previous();
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
	
	public boolean NavigateRight() {
		if(ListIteratorDirection) {
			if(mListIterator.hasNext()) {
				mNavigationStep = (NavigationStep) mListIterator.next();
				return true;
			} else {
				return false;
			}
		} else {
			ListIteratorDirection = LAST_ITERATION_NEXT;
			if(mListIterator.hasNext()) {
				mListIterator.next();
				if(mListIterator.hasNext()) {
					mNavigationStep = (NavigationStep) mListIterator.next();
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
	
}
