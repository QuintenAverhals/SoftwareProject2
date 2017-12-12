package dao;

import java.util.ArrayList;
import dao.LogfileDAO;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import base.Logfile;
import base.Login;
import base.Main;
import base.Main;
import base.SHA512;
import base.LoginController;
public class LoginDAO {
	
	private Logfile log= new Logfile();

	public boolean check(Login currentUser) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		Query query= session.createQuery("from Login where Username = :u and password= :p and Visibility=1");
		query.setParameter("u", currentUser.getUsername());
		query.setParameter("p", currentUser.getPassword());
		
		List<Login> user= query.list();
		session.getTransaction().commit();
		
		if(user.size() > 0)
			return true;
		else
			return false;
	}
	public static void createNewUser(String userName, String wachtwoord, boolean isAdmin, String email) throws Exception
	{
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		//These lines will call the sha function which will encrypt the password
		SHA512 hasher = new SHA512();
	
		Random r= new Random();
		int salt = r.nextInt(50);
		String password=hasher.hashString(wachtwoord+salt);
		System.out.println(password);
		 
		Login newLogin= new Login(userName,password,isAdmin,email,salt);
		
		session.save(newLogin);
		
		
		
		session.getTransaction().commit();
		
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" created user "+userName);
		
		
	}
	
	public boolean login(String userName, String passWord) throws Exception
	{
		
		System.out.println("Login' in----------------");
		
		//sha hashes the string right before checking password
		SHA512 hasher = new SHA512();
		
		int salt=LoginDAO.getSaltByUserName(userName);
		
		String password=hasher.hashString(passWord+salt);
		Login currentUser= new Login(userName,password);
		boolean result= currentUser.check(currentUser);
		
		if(result== true)
		{
			return true;
		}
		else {
			return false;
		}
		
		
	}
	public static int getSaltByUserName(String Username)
	{
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		
		
		
		Query query= session.createQuery("from Login where Username='"+Username+"'");
		
		
		Login l = (Login) query.uniqueResult();
		int salt= l.getSalt();
		
		
		session.getTransaction().commit();
		
		return salt;
	}
	void updatePassword(int id, String password, String newPassword) throws Exception
	{
		//functions asks currentPassword to check if current user is not a burgler :D
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		session.beginTransaction();
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		
		
		SHA512 hasher = new SHA512();
		String passWord=hasher.hashString(password);
		
		if(myLogin.getPassword().equals(passWord))
		{
			newPassword= hasher.hashString(newPassword);
			myLogin.setPassword(newPassword);
			//TODO
			//++ pop up :: password has been changed and ask password SECOND TIME! 
		}
		else {
			//TODO
			System.out.println("does not work");
			// pop up :: wrong passWord try again
		}
			
		session.getTransaction().commit();
		
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated password for userid "+id);
		
	}
	public void updateUserName(int id, String userName) throws Exception
	{
		
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setUsername(userName);
		
		
		session.getTransaction().commit();
		
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated username for userid "+id);
		
	
	
		
	}
	public void updateIsAdmin(int id, boolean isAdmin) throws Exception
	{
		
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setAdmin(isAdmin);
		
		
		session.getTransaction().commit();
		
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated admin rights for userid "+id);
		
		
	
		
	}
	public void updateAll(int id,String email, String username, String password, boolean isAdmin ) throws Exception
	{
		
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setAdmin(isAdmin);
		myLogin.setEmail(email);
		myLogin.setUsername(username);
		myLogin.setPassword(password);
		
		
		session.getTransaction().commit();
		
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated userid "+id);
	
		
	}
	public void isAdmin(int id, boolean admin)
	{
		
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setAdmin(admin);
		
	
			
		session.getTransaction().commit();
		
		
	}
	public static void deleteLogin(int id) throws Exception
	{
		
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setVisibility(0);
		session.update(myLogin);
	
			
		session.getTransaction().commit();
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" deleted userid "+id);
	
		
		
	}
public List<Login> getALL() {
		
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
	
		
		Query query= session.createQuery("from Login where Visibility=1");
		List<Login> users= query.list();
		
		session.getTransaction().commit();
		
		 
		return users;
	}
public List<Login> getUsersByName(String name) {
	
	
	Session session = Main.factory.getCurrentSession();
	session.beginTransaction(); 
	
	
	Query query= session.createQuery("from Login where Username='" + name+"'"+"and Visibility=1");
	List<Login> users= query.list();
	
	
	session.getTransaction().commit();
	

	 
	return users;
}
public Login getUsersByID(String id) {
	
	
	Session session = Main.factory.getCurrentSession();
	session.beginTransaction(); 
	
	
	Query query= session.createQuery("from Login where UserID='" + id+"'"+"and Visibility=1");
	//List<Login> users= query.list();
	Login user =  (Login) query.uniqueResult();
	
	
	session.getTransaction().commit();
	
	
	 
	return user;	
}
public List<Login> getUsersByNameAndEmail(String name, String email) {
	
	
	Session session = Main.factory.getCurrentSession();
	session.beginTransaction(); 

	
	Query query= session.createQuery("from Login where Username='" + name+"'and email='"+email+"'"+"and Visibility=1");
	List<Login> users= query.list();
	
	session.getTransaction().commit();
	

	 
	return users;
}
 
public boolean checkUsernameUnique(String name) {
	
	boolean result= false;
	
	Session session = Main.factory.getCurrentSession();
	session.beginTransaction(); 
	
	
	Query query= session.createQuery("from Login where Username='" + name+"'"+"and Visibility=1");
	List<Login> users= query.list();
	
	for(int i=0;i<users.size();i++)
	{
		if(users.get(i).getUsername().equals(name))
		{
			session.getTransaction().commit();
			return true; 
		}
	}
		
	session.getTransaction().commit(); 
	return result;
}


}


