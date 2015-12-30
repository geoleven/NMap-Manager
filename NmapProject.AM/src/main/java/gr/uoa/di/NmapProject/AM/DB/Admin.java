package gr.uoa.di.NmapProject.AM.DB;

/**
 * Class representing an administrator of the A.M..
 * 
 * @author George
 * 
 */
public class Admin {
	/**
	 * Administrator ID.
	 */
	public int id;
	/**
	 * Administrator user name (for login purposes).
	 */
	public String username;
	/**
	 * Administrator password (for login purposes).
	 */
	public String password;
	/**
	 * Administrator condition. If true, administrator can login and manage the
	 * A.M..
	 */
	public boolean active;

	/**
	 * Sets a new administrator.
	 * 
	 * @param id
	 *            Administrator ID.
	 * @param username
	 *            Administrator user name (for login purposes).
	 * @param password
	 *            Administrator password (for login purposes).
	 * @param active
	 *            Administrator condition. If true, administrator can login and
	 *            manage the A.M..
	 */
	public Admin(int id, String username, String password, boolean active) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.active = active;
	}

	/**
	 * Sets a new administrator.
	 * 
	 * @param id
	 *            Administrator ID.
	 * @param username
	 *            Administrator user name (for login purposes).
	 * @param password
	 *            Administrator password (for login purposes).
	 * @param active
	 *            Administrator condition. If true, administrator can login and
	 *            manage the A.M..
	 */
	public Admin(String username, String password, boolean active) {
		id = -1;
		this.username = username;
		this.password = password;
		this.active = active;
	}

}
