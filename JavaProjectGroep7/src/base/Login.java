package base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.LoginDAO;
@Entity
@Table(name="LOGIN")

public class Login extends LoginDAO{

	@Id
	@Column(name="UserID")
	private int user_ID;
	
	@Column(name="Username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="admin")
	private boolean Admin;
	
	@Column(name="email")
	private String email;
	
	@Column(name="Visibility")
	private int visibility;
	
	@Column(name="salt")
	private int salt;
	
	public Login()
	{
		
	}
	
	



	
	public int getSalt() {
		return salt;
	}






	public void setSalt(int salt) {
		this.salt = salt;
	}






	public Login(String username, String password, boolean admin, String email) {
		super();
		this.username = username;
		this.password = password;
		Admin = admin;
		this.email = email;
		this.visibility=1;
	}


	public Login(String username, String password, boolean admin, String email, int salt) {
		super();
		this.username = username;
		this.password = password;
		Admin = admin;
		this.email = email;
		this.visibility = 1;
		this.salt = salt;
	}






	public int isVisibility() {
		return visibility;
	}






	






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}










	public Login(String username, String password) {
		
		this.username = username;
		this.password = password;
	}




	






	public int getUser_ID() {
		return user_ID;
	}






	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}






	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public boolean isAdmin() {
		return Admin;
	}




	public void setAdmin(boolean admin) {
		Admin = admin;
	}

	@Override
	public String toString() {
		return "Login [user_ID=" + user_ID + ", username=" + username + ", password=" + password + ", Admin=" + Admin
				+ "]";
	}






	public int getVisibility() {
		return visibility;
	}






	public void setVisibility(int i) {
		this.visibility= i;
		
	}






	
	
}
