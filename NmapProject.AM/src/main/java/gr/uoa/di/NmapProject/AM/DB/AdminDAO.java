package gr.uoa.di.NmapProject.AM.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class that manages the database access for the administrator account changes.
 * 
 * @author George
 *
 */
public class AdminDAO {
	/**
	 * Inserts a new administrator in the database.
	 * 
	 * @param a
	 *            The administrator to insert.
	 */
	public static void create(Admin a) {
		Connection db = DB.connect();
		try {

			String query = " insert into admins (username , password , active)" + " values (?, ?, ?)";

			PreparedStatement preparedStmt = db.prepareStatement(query);

			preparedStmt.setString(1, a.username);
			preparedStmt.setString(2, a.password);
			preparedStmt.setBoolean(3, a.active);

			preparedStmt.execute();

			db.close();

		} catch (SQLException ex) {
			DB.SQLError(ex);
		}

	}

	/**
	 * @param username
	 *            The administrator's user name to search for.
	 * @return Returns the administrator corresponding to the aforementioned
	 *         user name if found in the database. Returns null if user name is
	 *         not found.
	 */
	public static Admin get(String username) {
		Connection db = DB.connect();
		try {

			String query = " SELECT * from admins WHERE username = '" + username + "'";

			Statement stmt = db.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			Admin res = null;
			if (rs.next()) {
				res = new Admin(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getBoolean("active"));
			}

			db.close();
			return res;

		} catch (SQLException ex) {
			DB.SQLError(ex);
		}
		return null;
	}

	/**
	 * Checks if the credentials given can be authenticated.
	 * 
	 * @param username
	 *            The administrator's user name.
	 * @param password
	 *            The administrator's password.
	 * @return True if the user name exists and the password is correct. Else
	 *         false.
	 */
	public static boolean authenticate(String username, String password) {
		Connection db = DB.connect();
		try {

			String query = " SELECT id from admins WHERE username = '" + username + "' AND password = '" + password
					+ "'";

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
}
