package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import base.Login;
import base.SHA512;

public class LoginDAO {
	

		
	
	public boolean check(Login currentUser) {
		
		boolean result = false;
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Login.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		Query query= session.createQuery("from Login where Username ='"+currentUser.getUsername()+ "' and password='"+currentUser.getPassword()+"'");
		List<Login> user= query.list();
		
		for(Login u: user)                       
		{
			if(currentUser.getUsername().equals(u.getUsername())&& (currentUser.getPassword().equals(u.getPassword())))
			{
				result= true;
			}
			
		}
		
		
		session.getTransaction().commit();
		System.out.println("Done!"); 
		session.close();
		sessionfactory.close();
		 
		return result;
	}
	
	public void createNewUser(String userName, String wachtwoord, boolean isAdmin, String email) throws Exception
	{
		
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Login.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		//These lines will call the sha function which will encrypt the password
		SHA512 hasher = new SHA512();
		String password=hasher.hashString(wachtwoord);
		
		Login newLogin= new Login(userName,password,isAdmin,email);
		
		session.save(newLogin);
		
		
		
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
		
		
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
		
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Login.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
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
		session.close();
		sessionfactory.close();
		
	}
	void updateUserName(int id, String userName)
	{
		
		
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Login.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setUsername(userName);
		
	
			
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
		
	}
	void isAdmin(int id, Boolean admin)
	{
		
		
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Login.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		
		Login myLogin= (Login) session.get(Login.class, id);
		myLogin.setAdmin(admin);
		
	
			
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
		
	}
public List<Login> getALL() {
		
		
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Login.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		Query query= session.createQuery("from Login");
		List<Login> users= query.list();
		
		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
		 
		return users;
	}

public static void main(String[] args) throws Exception {
	Login login= new Login("seppe123","bilal123");
	login.createNewUser("seppe", "seppe1234", true,"seppe@student.ehb.be");
	
	
}

}
