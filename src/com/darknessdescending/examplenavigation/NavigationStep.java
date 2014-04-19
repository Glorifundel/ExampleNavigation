package com.darknessdescending.examplenavigation;

import java.util.*;

public class NavigationStep
{
	NavigationStep ParentStep;
	Object objectStep;	
	
	public NavigationStep(Object step) {
		objectStep = step;
	}
	
	public Object getStep() { return objectStep; }
	public NavigationStep getParentStep() { return ParentStep; }
	
	public void setStep(Object step) { objectStep = step; }
	public void setParentStep(NavigationStep parent) { ParentStep = parent; }
}
