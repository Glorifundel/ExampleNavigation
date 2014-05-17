package com.darknessdescending.examplenavigation;

import java.util.*;

public class NavigationStep
{
	private NavigationStep ParentStep;
	private Object mStep;
	private int mType = -1;	
	
	public NavigationStep() {
		
	}
	
	public NavigationStep(Object step) {
		setStep(step);
	}
	
	public NavigationStep(Object step, NavigationStep parent) {
		setStep(step);
		setParentStep(parent);
	}
	
	public NavigationStep(Object step, int type) {
		setStep(step);
		setType(type);
	}
	
	public NavigationStep(Object step, NavigationStep parent, int type) {
		setStep(step);
		setParentStep(parent);
		setType(type);
	}
	
	public NavigationStep getParentStep() { return ParentStep; }
	public Object getStep() { return mStep; }
	public int getType() { return mType; }
	
	public void setParentStep(NavigationStep parent) { ParentStep = parent; }
	public void setStep(Object step) { mStep = step; }
	public void setType(int type) { mType = type; }
}
