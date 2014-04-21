package com.darknessdescending.examplenavigation;

import java.util.*;

public class NavigationStep
{
	private NavigationStep ParentStep;
	private Object mStep;
	private int mType;	
	
	public NavigationStep() {
		
	}
	
	public NavigationStep getParentStep() { return ParentStep; }
	public Object getStep() { return mStep; }
	public int getType() { return mType; }
	
	public void setParentStep(NavigationStep parent) { ParentStep = parent; }
	public void setStep(Object step) { mStep = step; }
	public void setType(int type) { mType = type; }
}
