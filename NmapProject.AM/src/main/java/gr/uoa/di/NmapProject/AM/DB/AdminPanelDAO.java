package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class AdminPanelDAO {
	public static LinkedList<SA> getPendReg() {
		LinkedList<SA> results = new LinkedList<SA>();		
		Connection db = DB.connect();
		try{		
			String query = " SELECT * from software_agents WHERE is_accepted = 0";
			Statement stmt = db.createStatement();			
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				results.add(new SA(rs.getInt(1), 
								rs.getString(2), 
								rs.getString(3), 
								rs.getString(4), 
								rs.getString(6), 
								rs.getString(7), 
								rs.getString(5)));
			}
			db.close();
		} catch (SQLException ex){
			DB.SQLError(ex);
		}
		return results;		
	}
}
