package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SADAO {
	public static boolean exists(String hash){
		Connection db = DB.connect();
		try{
			
			String query = " SELECT * from software_agents WHERE hash = '"+hash+"'";
			
			Statement stmt = db.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			boolean found = false;
			if(rs.next()){
				found = true;
			}
			
			db.close();
			return found;
			
		} catch (SQLException ex){
			DB.SQLError(ex);
		}
		return false;
	}
	
	public static void add(SA sa){
		Connection db = DB.connect();
		try{
			
			String query = " insert into software_agents (device_name , ip , mac_address , nmap_version , hash)"
				+ " values (? , ? , ? , ? , ?)";
			
			PreparedStatement preparedStmt = db.prepareStatement(query);
			
			preparedStmt.setString (1, sa.device_name);
			preparedStmt.setString (2, sa.ip);
			preparedStmt.setString (3, sa.mac_address);
			preparedStmt.setString (4, sa.nmap_version);
			preparedStmt.setString (5, sa.hash);
			
			preparedStmt.execute();
			
			db.close();
			
		} catch (SQLException ex){
			DB.SQLError(ex);
		}	
	}
}
