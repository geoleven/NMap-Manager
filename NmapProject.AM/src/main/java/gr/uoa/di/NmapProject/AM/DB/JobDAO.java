package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import gr.uoa.di.NmapProject.AM.GUI.JobPrev;

/**
 * Data Access Class for queries for jobs
 * @author George
 *
 */
public class JobDAO {
	/**
	 * 
	 * Returns jobs for a specific sa
	 * Jobs statuses are either
	 * Pending for new jobs
	 * or Delete to stop periodic jobs
	 * 
	 * @param saID
	 * 		id of the sa
	 * @return
	 * 		list of jobs
	 */
	public static LinkedList<Job> getAllSAJobs(int saID) {
		LinkedList<Job> saJobs = new LinkedList<Job>();
		Connection db = DB.connect();
		Job temp;

		try {
			String query = " SELECT * from jobs j WHERE j.sa_id = " + saID + " AND (j.status = \"Pending\" OR j.status = \"Delete\")";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				temp = new Job(rs.getInt(1), rs.getString(2), rs.getBoolean(4), rs.getInt(3), rs.getInt(6),
						rs.getString(5));
				saJobs.add(temp);
				
				query = "UPDATE jobs SET status = ? WHERE id = ? AND status= ?";
					
				PreparedStatement preparedStmt = db.prepareStatement(query);
				
				preparedStmt.setString (1, "Executing");
				preparedStmt.setInt (2, rs.getInt(1));
				preparedStmt.setString(3, "Pending");
				
				preparedStmt.execute();
				
				preparedStmt = db.prepareStatement(query);
				
				preparedStmt.setString (1, "Stopped");
				preparedStmt.setInt (2, rs.getInt(1));
				preparedStmt.setString(3, "Delete");
				
				preparedStmt.execute();
				
			}
			
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return saJobs;
	}
	
	/**
	 * Insert a new job to db
	 * @param job
	 * @return
	 * 		success or fail
	 */
	public static boolean newJob(Job job) {
		Connection db = DB.connect();

		try {

			String query = " insert into jobs (parameters , time , periodic , status , sa_id)"
					+ " values (? , ? , ? , ? , ?)";

			PreparedStatement preparedStmt = db.prepareStatement(query);

			preparedStmt.setString(1, job.parameters);
			preparedStmt.setInt(2, job.period);
			preparedStmt.setBoolean(3, job.periodic);
			preparedStmt.setString(4, job.status);
			preparedStmt.setInt(5, job.saID);

			preparedStmt.execute();

			db.close();

			return true;

		} catch (SQLException ex) {
			DB.SQLError(ex);
		}

		return false;
	}
	
	/**
	 * It makes a general job class from the S.A.'s hash.
	 * @param params Jobs parameters.
	 * @param isPeriodic Boolean showing if job is periodic.
	 * @param period The period of the job if periodic.
	 * @param saHash The S.A.'s hash.
	 * @return The new job that it created.
	 */
	public static boolean newJob(String params, boolean isPeriodic, int period, String saHash) {

		int saID = SADAO.hashToId(saHash);

		return newJob(new Job(params, isPeriodic, period, saID, "Pending"));
	}
	
	/**
	 * Retrieves the S.A. of job.
	 * 
	 * @param id
	 *            The job's id to search its S.A..
	 * @return The S.A.'s id.
	 */
	public static int sa(int id) {
		Connection db = DB.connect();
		int saID = 0;

		try {
			String query = " SELECT sa_id FROM jobs WHERE id = " + id;
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				saID = rs.getInt(1);
			}

			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}

		return saID;
	}
	
	/**
	 * 
	 * Set the status of a periodic job to delete 
	 * 
	 * @param id
	 * 		jobs id
	 */
	public static void setStatusToDelete(int id){
		Connection db = DB.connect();

		try {

			String query = "UPDATE jobs SET status = ? WHERE id = ? ";

			PreparedStatement preparedStmt = db.prepareStatement(query);

			preparedStmt.setString(1, "Delete");
			preparedStmt.setInt(2, id);
			
			preparedStmt.execute();

			db.close();

		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
	}
	
	public static LinkedList<Map> getResults(String saHash , int number){
		LinkedList<Map> results = new LinkedList<Map>();
	
		Connection db = DB.connect();
		try {
			
			String query = "SELECT s.hash , j.parameters , j.time , j.periodic , r.xml , r.time_created "
							+ "FROM results r , jobs j , software_agents s "
							+ "WHERE j.id = r.jobs_id AND s.id = r.software_agents_id AND s.hash = ?"
							+ "ORDER BY time_created DESC "
							+ "LIMIT 0 , ?;";
			
			PreparedStatement stmt = db.prepareStatement(query);
			stmt.setString(1, saHash);
			stmt.setInt(2, number);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Map res = new HashMap();
				res.put("hash", rs.getString("hash"));
				res.put("parameters", rs.getString("parameters"));
				res.put("time", rs.getInt("time"));
				res.put("periodic", rs.getInt("periodic"));
				res.put("xml", rs.getString("xml"));
				res.put("time_created", rs.getString("time_created"));
				results.add(res);
			}
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		
		return results;
	}
	
	public static LinkedList<Map> getAllResults(int number){
		LinkedList<Map> results = new LinkedList<Map>();
	
		Connection db = DB.connect();
		try {
			
			String query = "SELECT s.hash , j.parameters , j.time , j.periodic , r.xml , r.time_created "
							+ "FROM results r , jobs j , software_agents s "
							+ "WHERE j.id = r.jobs_id AND s.id = r.software_agents_id "
							+ "ORDER BY time_created DESC "
							+ "LIMIT 0 , ?;";
			
			PreparedStatement stmt = db.prepareStatement(query);
			stmt.setInt(1, number);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Map res = new HashMap();
				res.put("hash", rs.getString("hash"));
				res.put("parameters", rs.getString("parameters"));
				res.put("time", rs.getInt("time"));
				res.put("periodic", rs.getInt("periodic"));
				res.put("xml", rs.getString("xml"));
				res.put("time_created", rs.getString("time_created"));
				results.add(res);
			}
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		
		return results;
	}
	
	public static LinkedList<Map> getPeriodicJobsOfSA(String curSA) {
		LinkedList<Map> results = new LinkedList<Map>();
		Connection db = DB.connect();
		try {
			String query = "SELECT j.id, j.parameters, j.time, j.periodic FROM jobs j, software_agents sa WHERE sa.hash = \""
					+ curSA + "\" AND j.sa_id = sa.id AND status =\"Executing\" ";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				Map m = new LinkedHashMap();
				
				m.put("id", rs.getInt(1));
				m.put("parameters", rs.getString(2));
				m.put("time", rs.getInt(3));
				m.put("periodic", rs.getInt(4));
				
				results.add(m);
			}
			;
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return results;
	}
	
	public static LinkedList<Map> getRecentJobs(){
		LinkedList<Map> results = new LinkedList<Map>();
		Connection db = DB.connect();
		try {
			String query = "SELECT j.id, j.parameters, j.time, j.periodic FROM jobs j ORDER BY j.id DESC LIMIT 0 , 10";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				Map m = new LinkedHashMap();
				
				m.put("id", rs.getInt(1));
				m.put("parameters", rs.getString(2));
				m.put("time", rs.getInt(3));
				m.put("periodic", rs.getInt(4));
				
				results.add(m);
			}
			;
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return results;
	}
	
	public static LinkedList<Map> getRecentJobs(String hash){
		LinkedList<Map> results = new LinkedList<Map>();
		Connection db = DB.connect();
		try {
			String query = "SELECT j.id, j.parameters, j.time, j.periodic FROM jobs j, software_agents sa WHERE sa.hash = \""
					+ hash + "\" AND j.sa_id = sa.id ORDER BY j.id DESC LIMIT 0 , 10";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				Map m = new LinkedHashMap();
				
				m.put("id", rs.getInt(1));
				m.put("parameters", rs.getString(2));
				m.put("time", rs.getInt(3));
				m.put("periodic", rs.getInt(4));
				
				results.add(m);
			}
			;
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return results;
	}

}
