package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class JobDAO {

	public static LinkedList<Job> getAllSAJobs(int saID) {
		LinkedList<Job> saJobs = new LinkedList<Job>();
		Connection db = DB.connect();
		Job temp;

		try {
			String query = " SELECT * from jobs j WHERE j.sa_id = " + saID + " AND j.status = \"Pending\"";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				temp = new Job(rs.getInt(1), rs.getString(2), rs.getBoolean(4), rs.getInt(3), rs.getInt(6),
						rs.getString(5));
				saJobs.add(temp);
				
				query = "UPDATE jobs SET status = ? WHERE id = ?";
					
				PreparedStatement preparedStmt = db.prepareStatement(query);
				
				preparedStmt.setString (1, "Executing");
				preparedStmt.setInt (2, rs.getInt(1));
				
				preparedStmt.execute();
				
			}
			
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return saJobs;
	}
	
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

}
