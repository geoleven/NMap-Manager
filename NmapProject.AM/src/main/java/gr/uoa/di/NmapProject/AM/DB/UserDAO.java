package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author George
 *	
 *	Data Access Class for all the User related queries	
 *
 */
public class UserDAO {
	/**
	 * Stores a new User to the DB
	 * 
	 */
	public static void add(User user){
		Connection db = DB.connect();
		try {

			String query = " INSERT INTO users (email , password , is_accepted) VALUES(? , ? , ?)";
			PreparedStatement preparedStmt = db.prepareStatement(query);
			preparedStmt.setString(1, user.email);
			preparedStmt.setString(2, user.password);
			preparedStmt.setBoolean(3, user.is_accepted);
			preparedStmt.execute();
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
	}
	/**
	 * returns true if given user is registered
	 * false otherwise
	 */
	public static boolean isAccepted(String email) {
		Connection db = DB.connect();
		try {
			String query = " SELECT * FROM users WHERE email = '" + email + "' AND is_accepted = 1";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			boolean found = false;
			if (rs.next()) {
				found = true;
			}
			db.close();
			return found;
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return false;
	}
	/**
	 * 
	 * returns true if there s a User With that email in the DB 
	 * 		   false otherwise
	 */
	public static boolean exists(String email) {
		Connection db = DB.connect();
		try {
			String query = " SELECT * from users WHERE email = '" + email + "'";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			boolean found = false;
			if (rs.next()) {
				found = true;
			}
			db.close();
			return found;
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return false;
	}
	

	public static boolean correctPass(String email , String pass){
		Connection db = DB.connect();
		try {
			String query = " SELECT * from users WHERE email = '" + email + "' AND password = '"+pass+"'";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			boolean found = false;
			if (rs.next()) {
				found = true;
			}
			db.close();
			return found;
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return false;
	}
	
}
