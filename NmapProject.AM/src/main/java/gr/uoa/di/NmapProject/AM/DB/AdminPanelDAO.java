package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class AdminPanelDAO {
	public static LinkedList<SA> getPendReg() {
		LinkedList<SA> results = new LinkedList<SA>();		
		Connection db = DB.connect();
		try{		
			String query = " SELECT * FROM software_agents WHERE is_accepted = 0";
			Statement stmt = db.createStatement();			
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				results.add(new SA(rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3), 
								rs.getString(4), 
								rs.getString(6), 
								rs.getString(7), 
								rs.getString(5)));
			};
			db.close();
		} catch (SQLException ex){
			DB.SQLError(ex);
		}
		return results;		
	}
	
	public static void acceptSAs(LinkedList<Integer> ids) {
		Connection db = DB.connect();
		try{		
			String query = " UPDATE software_agents SET is_accepted = 1 WHERE id = ?";
			PreparedStatement stmt = db.prepareStatement(query);			
			for (int id : ids) {
				stmt.setInt(1, id);
				stmt.executeUpdate();
				
			}
			db.close();
		} catch (SQLException ex){
			DB.SQLError(ex);
		}
		return;	
	}
	
	public static LinkedList<SAInfoStatus> getAcceptedSAInfo() {
		LinkedList<SAInfoStatus> results = new LinkedList<SAInfoStatus>();		
		Connection db = DB.connect();
		try{		
			String query = " SELECT * FROM software_agents WHERE is_accepted = 1";
			Statement stmt = db.createStatement();			
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				//TODO add real status
				results.add(new SAInfoStatus(rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3), 
								rs.getString(4), 
								rs.getString(5), 
								rs.getString(6), 
								rs.getString(7),
								rs.getBoolean(8),
								//TODO HERE
								true));
			};
			db.close();
		} catch (SQLException ex){
			DB.SQLError(ex);
		}
		return results;		
	}
}
