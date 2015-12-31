package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * 
 * Data Access class for result related queries
 * 
 * @author George
 *
 */
public class ResultDAO {
	/**
	 * insert a new result to db
	 * @param res
	 */
	public static boolean insert(Result res){
		Connection db = DB.connect();
		
		try{
			
			String query = " INSERT INTO results( xml , jobs_id , software_agents_id , time_created )"
				+ " values (? , ? , ? , NOW())";
			
			PreparedStatement preparedStmt = db.prepareStatement(query);
			
			preparedStmt.setString (1, res.xml);
			preparedStmt.setInt (2, res.jobs_id);
			preparedStmt.setInt (3, res.software_agents_id);;
			
			preparedStmt.execute();
			
			db.close();
			
			return true;
			
		} catch (SQLException ex){
			DB.SQLError(ex);
		}
			
		return false;
	}
	
}
