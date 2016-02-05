package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * Data Access Class for all the SA related queries
 * 
 * @author George
 *
 */
public class SADAO {
	/**
	 * 
	 * returns true if an SA exists in db with given hash
	 * 		   false otherwise
	 */
	public static boolean exists(String hash) {
		Connection db = DB.connect();
		try {
			String query = " SELECT * from software_agents WHERE hash = '" + hash + "'";
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
	 * returns true if SA with given has is registered
	 * false otherwise
	 * 
	 */
	public static boolean isAccepted(String hash) {
		Connection db = DB.connect();
		try {
			String query = " SELECT * from software_agents WHERE hash = '" + hash + "' AND is_accepted = 1";
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
	 * adds an SA to db
	 * 
	 * @param sa
	 */
	public static void add(SA sa) {
		Connection db = DB.connect();
		try {

			String query = " insert into software_agents (device_name , ip , mac_address , nmap_version , hash , os_version)"
					+ " values(? , ? , ? , ? , ? , ?)";
			PreparedStatement preparedStmt = db.prepareStatement(query);
			preparedStmt.setString(1, sa.device_name);
			preparedStmt.setString(2, sa.ip);
			preparedStmt.setString(3, sa.mac_address);
			preparedStmt.setString(4, sa.nmap_version);
			preparedStmt.setString(5, sa.hash);
			preparedStmt.setString(6, sa.os_version);
			preparedStmt.execute();
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
	}
	
	/**
	 * return the id of an SA given the hash
	 * @param myHash
	 */
	public static int hashToId(String myHash) {
		Connection db = DB.connect();
		int found = 0;
		try {
			String query = " SELECT sa.id FROM software_agents sa WHERE sa.hash = '" + myHash + "'";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				found = rs.getInt(1);
			}
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return found;
	}
	
}
