package com.fwumdesoft.api.annotation;

import java.lang.annotation.Documented;

/**
 * Annotation used to annotate when something is unfinished.<br>
 * Priority is annotates how much of a priority it is to complete the annotated method or class.
 * @author Jason Carrete
 */
@Documented
public @interface Unfinished
{
	public enum Priority {LOW, MEDIUM, HIGH}
	
	Priority value();
}
