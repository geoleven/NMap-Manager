package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import gr.uoa.di.NmapProject.AM.GUI.JobPrev;
import gr.uoa.di.NmapProject.AM.Server.OnlineStatus;

/**
 * Database access class for the Administrator Panel window.
 * @author George
 * 
 */
public class AdminPanelDAO {
	
	/**
	 * Retrieves from database the users that are still pending for
	 * their acceptance.
	 * 
	 * @return A map with all the user registrations at this time.
	 */
	public static Map<Integer, String> getAdminReg() {
		Map<Integer, String> results = new LinkedHashMap<Integer, String>();
		Connection db = DB.connect();
		try {
			String query = "SELECT id, email FROM users WHERE is_accepted = 0";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				results.put(rs.getInt("id"), rs.getString("email"));
			}
			;
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return results;
	}
	
	
	/**
	 * Retrieves from database the registrations that are still pending for
	 * their acceptance.
	 * 
	 * @return A linked list with all the pending registrations at this time.
	 */
	public static LinkedList<SA> getPendReg() {
		LinkedList<SA> results = new LinkedList<SA>();
		Connection db = DB.connect();
		try {
			String query = "SELECT * FROM software_agents WHERE is_accepted = 0";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				results.add(new SA(rs.getInt("id"), rs.getString("device_name"), rs.getString("ip"), rs.getString("mac_address"), rs.getString("nmap_version"),
						rs.getString("hash"), rs.getString("os_version")));
			}
			;
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return results;
	}

	/**
	 * Changes the status of the given S.A.s to accepted.
	 * 
	 * @param ids
	 *            A linked list with the ids of the S.A.s to accept.
	 */
	public static void acceptSAs(LinkedList<Integer> ids) {
		Connection db = DB.connect();
		try {
			String query = "UPDATE software_agents SET is_accepted = 1 WHERE id = ?";
			PreparedStatement stmt = db.prepareStatement(query);
			for (int id : ids) {
				stmt.setInt(1, id);
				stmt.executeUpdate();

			}
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return;
	}
	
	/**
	 * Changes the status of the given admins to accepted.
	 * 
	 * @param ids
	 *            A linked list with the ids of the admins to accept.
	 */
	public static void acceptAdmins(LinkedList<Integer> ids) {
		Connection db = DB.connect();
		try {
			String query = "UPDATE users SET is_accepted = 1 WHERE id = ?";
			PreparedStatement stmt = db.prepareStatement(query);
			for (int id : ids) {
				stmt.setInt(1, id);
				stmt.executeUpdate();

			}
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return;
	}
	
	/**
	 * Changes the status of the given S.A.s to rejected (deletes them).
	 * 
	 * @param ids
	 *            A linked list with the ids of the S.A.s to accept.
	 */
	public static void rejectSAs(LinkedList<Integer> ids) {
		Connection db = DB.connect();
		try {
			String query = "DELETE FROM software_agents WHERE id = ?";
			PreparedStatement stmt = db.prepareStatement(query);
			for (int id : ids) {
				stmt.setInt(1, id);
				stmt.executeUpdate();

			}
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return;
	}
	
	/**
	 * Changes the status of the given admins to rejected (deletes them).
	 * 
	 * @param ids
	 *            A linked list with the ids of the admins to accept.
	 */
	public static void rejectAdmins(LinkedList<Integer> ids) {
		Connection db = DB.connect();
		try {
			String query = "DELETE FROM users WHERE id = ?";
			PreparedStatement stmt = db.prepareStatement(query);
			for (int id : ids) {
				stmt.setInt(1, id);
				stmt.executeUpdate();

			}
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return;
	}

	/**
	 * Retrieves from the database the info for every accepted S.A..
	 * 
	 * @return Returns a linked list with the S.A. status and info.
	 * 
	 */
	public static LinkedList<SAInfoStatus> getAcceptedSAInfo() {
		LinkedList<SAInfoStatus> results = new LinkedList<SAInfoStatus>();
		Connection db = DB.connect();
		try {
			String query = "SELECT * FROM software_agents WHERE is_accepted = 1";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				SAInfoStatus sa = new SAInfoStatus(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8),
						true);
				
				sa.status = OnlineStatus.getInstance().isOnline(sa.unionHash);
				
				results.add(sa);
			}
			
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return results;
	}

	/**
	 * Retrieves from database the periodic jobs for a specific S.A..
	 * 
	 * @param curSA
	 *            The S.A. for which to retrieve the jobs.
	 * @return A linked list of periodic jobs for the given S.A..
	 */
	public static LinkedList<JobPrev> getPeriodicJobsOfSA(String curSA) {
		LinkedList<JobPrev> results = new LinkedList<JobPrev>();
		Connection db = DB.connect();
		try {
			String query = "SELECT j.id, j.parameters, j.time, j.periodic FROM jobs j, software_agents sa WHERE sa.hash = \""
					+ curSA + "\" AND j.sa_id = sa.id AND status =\"Executing\" ";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				results.add(new JobPrev(rs.getInt(1), rs.getString(2), rs.getInt(3),
						rs.getBoolean(4) ? "Periodic" : "Not Periodic"));
			}
			;
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return results;
	}

	/**
	 * Retrieves from the database all the info for only the online S.A.s.
	 * 
	 * @return A linked list the S.A. info.
	 */
	public static LinkedList<SAInfoStatus> getOnlineSAInfo() {
		LinkedList<SAInfoStatus> temp = getAcceptedSAInfo();
		LinkedList<SAInfoStatus> results = new LinkedList<SAInfoStatus>();
		for (SAInfoStatus sa : temp)
			if (sa.status)
				results.add(sa);
		return results;
	}

	/**
	 * Retrieves a list with S.A.s which have some results in the database.
	 * 
	 * @return A linked list with the hashes of the S.A.s.
	 */
	public static LinkedList<String> geSAWithResults() {
		LinkedList<String> results = new LinkedList<String>();
		Connection db = DB.connect();
		try {
			String query = "SELECT sa.hash FROM software_agents sa, (SELECT DISTINCT software_agents_id FROM results) as r1 WHERE r1.software_agents_id = sa.id";
			Statement stmt = db.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				results.add(rs.getString(1));
			}
			;
			db.close();
		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return results;
	}

	/**
	 * Retrieves from the database the jobs results of a specific S.A. or for
	 * all the S.A.s for a specific time.
	 * 
	 * @param saHash
	 *            The S.A. for which to retrieve the results. It will be
	 *            disposed if the function is used for all the S.A.s.
	 * @param sTime
	 *            The starting time from which to retrieve the results.
	 * @param eTime
	 *            The ending time until which to retrieve the results.
	 * @param forAll
	 *            True to retrieve results for every S.A.. False for a specific
	 *            S.A..
	 * @return A linked list with the XMLs of the results.
	 */
	public static LinkedList<String> getResultsOfSABetweenTime(String saHash, long sTime, long eTime, boolean forAll) {
		int saID = SADAO.hashToId(saHash);
		LinkedList<String> results = new LinkedList<String>();
		if(forAll)
			saID = 1;
		if (saID > 0) {
			Connection db = DB.connect();
			try {
				String query;
				if(forAll)
					query = "SELECT xml FROM results WHERE time_created >= ? AND time_created <= ?";
				else
					query = "SELECT xml FROM results WHERE software_agents_id = " + saID + " AND time_created >= ? AND time_created <= ?";
				PreparedStatement stmt = db.prepareStatement(query);
				stmt.setTimestamp(1, new Timestamp(sTime));
				stmt.setTimestamp(2, new Timestamp(eTime));
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					results.add(rs.getString(1));
				}
				;
				db.close();
			} catch (SQLException ex) {
				DB.SQLError(ex);
			}
		}
		return results;
	}
}
