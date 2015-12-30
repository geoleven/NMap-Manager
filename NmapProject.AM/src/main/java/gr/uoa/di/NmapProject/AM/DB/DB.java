package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class that holds all the info for connection to the database.
 * 
 * @author George
 *
 */
public class DB {
	
	private static final String host = "localhost";
	private static final String dbname = "nmapproject";
	private static final String username = "root";
	private static final String password= "root";
	
	/**
	 * Connects to the database.
	 * 
	 * @return A connection to the database if it could connect, else null.
	 */
	public static Connection connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://"+
			host+"/"+dbname+"?user="+username+"&password="+password);	
			
			return conn;
		} catch (SQLException ex) {
		    SQLError(ex);
		} catch (ClassNotFoundException ex){
			System.out.println("EX : "+ex.getMessage());
			System.exit(-1);
		}
		return null;
	}
	
	/**
	 * Handles the SQL exceptions.
	 * 
	 * @param ex The exception to handle.
	 */
	public static void SQLError(SQLException ex){
		// handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	    System.exit(-1);
	}
}
