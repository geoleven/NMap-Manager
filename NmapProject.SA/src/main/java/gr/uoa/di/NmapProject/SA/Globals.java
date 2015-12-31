package gr.uoa.di.NmapProject.SA;


/**
 * 
 * Set of Global variables
 * 
 * @author George
 *
 */
public class Globals {
	/**
	 * Where to look for config file
	 */
	public static String pathName = System.getProperty("user.home") + "/.myNmap/";
	/**
	 * Set to true for debugging messages else false
	 */
	public static boolean verbose;
	/**
	 * Size of thread pool for one time jobs
	 */
	public static int oneTimeJobThreadsNumber = 0;
	/**
	 * AM base url
	 */
	public static String baseURL = "http://localhost:8080/am/";
	/**
	 * local property
	 */
	public static boolean local = false;
	/**
	 * if != null sa this custom hash (use this if you want to run multiple SAs from the same machine) 
	 */
	public static String saHash = "cheat_SA_1";
}
