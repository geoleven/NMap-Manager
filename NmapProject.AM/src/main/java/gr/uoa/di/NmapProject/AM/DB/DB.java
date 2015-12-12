package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	public static final String host = "localhost";
	public static final String dbname = "nmapproject";
	public static final String username = "root";
	public static final String password= "root";
	
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
	
	public static void SQLError(SQLException ex){
		// handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
	    System.exit(-1);
	}
}
