package gr.uoa.di.NmapProject.AM;

import java.util.LinkedList;

/**
 * A class that holds all the global variables that may be needed.
 * 
 * @author George
 *
 */
public class Globals {
	/**
	 * The rate in which the requests happen and the online status is checked.
	 */
	public static final int refreshRate = 2;
	
	/**
	 * A boolean variable managing if more verbose output will be used.
	 */
	public static final boolean verbose = true;
	
	/**
	 * A list holding all the online S.A.s info.
	 */
	public LinkedList<String> onlineSAs = new LinkedList<String>();
}
