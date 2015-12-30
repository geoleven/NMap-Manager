package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;

import gr.uoa.di.NmapProject.AM.GUI.JobPrev;
import gr.uoa.di.NmapProject.AM.Server.OnlineStatus;

public class AdminPanelDAO {
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

	public static LinkedList<JobPrev> getPeriodicJobsOfSA(String curSA) {
		LinkedList<JobPrev> results = new LinkedList<JobPrev>();
		Connection db = DB.connect();
		try {
			String query = "SELECT j.id, j.parameters, j.time, j.periodic FROM jobs j, software_agents sa WHERE sa.hash = \""
					+ curSA + "\" AND j.sa_id = sa.id";
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

	public static LinkedList<SAInfoStatus> getOnlineSAInfo() {
		LinkedList<SAInfoStatus> temp = getAcceptedSAInfo();
		LinkedList<SAInfoStatus> results = new LinkedList<SAInfoStatus>();
		for (SAInfoStatus sa : temp)
			if (sa.status)
				results.add(sa);
		return results;
	}

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
