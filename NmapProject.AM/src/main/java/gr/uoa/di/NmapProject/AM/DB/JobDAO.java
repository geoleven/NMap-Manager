package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
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
			}
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return saJobs;
	}

}
