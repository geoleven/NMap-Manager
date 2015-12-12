package main.java.gr.uoa.di.NmapProject.AM.DB;

public class Admin {
	public int id;
	public String username;
	public String password;
	public boolean active;
	
	public Admin(int id , String username , String password , boolean active){
		this.id = id;
		this.username = username;
		this.password = password;
		this.active=  active;
	}
	
	public Admin(String username , String password , boolean active){
		id = -1;
		this.username = username;
		this.password = password;
		this.active=  active;
	}
	
}
