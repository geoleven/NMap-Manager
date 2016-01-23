package gr.uoa.di.NmapProject.AM.DB;

import org.json.simple.JSONObject;

/**
 * 
 * Users information
 * 
 * @author George
 *
 */
public class User {
	/**
	 * id of User
	 */
	public int id;
	/**
	 * Email
	 */
	public String email;
	/**
	 * Password
	 */
	public String password;
	/**
	 * boolean that shows if the user is registered or not
	 */
	public boolean is_accepted;
	
	/**
	 * Simple constructor
	 */
	public User(String email , String password){
		id = -1;
		this.email= email;
		this.password = password;
		is_accepted = false;
	}
	
	/**
	 * Full Constructor
	 */
	public User(int id , String email , String password , boolean is_accepted){
		this.id = id;
		this.email= email;
		this.password = password;
		this.is_accepted = is_accepted;
	}
	
	/**
	 * Constructor using json object
	 */
	public User(JSONObject user){
		email = (String) user.get("email");
		password = (String) user.get("password");
	}
	
	/**
	 * Print users information
	 */
	public void print(){
		System.out.println("["+id+" , "+email+" , "+password+" , "+is_accepted+"]");
	}
}
