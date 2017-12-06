package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import base.Login;
import base.Main;
import base.SHA512;
public class LoginDAO {
	


	public boolean check(Login currentUser) {
		
		boolean result = false;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		Query query= session.createQuery("from Login where Username ='"+currentUser.getUsername()+ "' and password='"+currentUser.getPassword()+"'"+"and Visibility=1");
		List<Login> user= query.list();
		
		for(Login u: user)                       
		{
			if(currentUser.getUsername().equals(u.getUsername())&& (currentUser.getPassword().equals(u.getPassword())))
			{
				result= true;
			}
			
		}
		
		
		
		session.getTransaction().commit();
		
		 
		return result;
	}
	
	public void createNewUser(String userName, String wachtwoord, boolean isAdmin, String email) throws Exception
	{
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		//These lines will call the sha function which will encrypt the password
		SHA512 hasher = new SHA512();
		String password=hasher.hashString(wachtwoord);
		
		Login newLogin= new Login(userName,password,isAdmin,email);
		
		session.save(newLogin);
		
		
		
		session.getTransaction().commit();
		
		
	}
	
	public boolean login(String userName, String passWord) throws Exception
	{
		
		System.out.println("Login' in----------------");
		
		//sha hashes the string right before checking password
		SHA512 hasher = new SHA512();
		String password=hasher.hashString(passWord);
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
		
		
		
	}
	public void updateUserName(int id, String userName)
	{
		
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setUsername(userName);
		
		
		session.getTransaction().commit();
		
	
	
		
	}
	public void updateIsAdmin(int id, boolean isAdmin)
	{
		
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setAdmin(isAdmin);
		
		
		session.getTransaction().commit();
		
		
	
		
	}
	public void updateAll(int id,String email, String username, String password, boolean isAdmin )
	{
		
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setAdmin(isAdmin);
		myLogin.setEmail(email);
		myLogin.setUsername(username);
		myLogin.setPassword(password);
		
		
		session.getTransaction().commit();
		
	
		
	}
	public void isAdmin(int id, boolean admin)
	{
		
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setAdmin(admin);
		
	
			
		session.getTransaction().commit();
		
		
	}
	public static void deleteLogin(int id)
	{
		
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setVisibility(0);
		session.update(myLogin);
	
			
		session.getTransaction().commit();
	
		
		
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


