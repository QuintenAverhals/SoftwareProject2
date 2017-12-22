package base;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name="LOGIN_WEBSITE")
public class LoginWebsite {
	
	@Id
	@Column(name="LoginID")
	private int loginID;

	@Column(name="WebsiteUserName")
	private String UserName;

	@Column(name="Wachtwoord ")
	private String Wachtwoord;

	public LoginWebsite(int loginID, String userName, String wachtwoord) {
		super();
		this.loginID = loginID;
		UserName = userName;
		Wachtwoord = wachtwoord;
	}

	public LoginWebsite(String userName, String wachtwoord) {
		super();
		UserName = userName;
		Wachtwoord = wachtwoord;
	}

	public LoginWebsite(String userName) {
		super();
		UserName = userName;
	}

	public int getLoginID() {
		return loginID;
	}

	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getWachtwoord() {
		return Wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		Wachtwoord = wachtwoord;
	}
	
	public LoginWebsite() {
		super();
	}

	public static Boolean bestaatUserName(String userName){
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM LoginWebsite WHERE UserName = "+ userName);
		List<LoginWebsite> logins = query.list();
		
		if(logins.size() > 0) {
			System.out.println("Ja");
		}
		else {
			System.out.println("Nee");
		}	
		
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
	
		
		
		return true;
	}	
	
	public static int getLoginIDFromUserName(String userName) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("FROM LoginWebsite WHERE UserName = "+ userName);
		List<LoginWebsite> users = query.list();
		LoginWebsite user = users.get(0);
		int loginID = user.getLoginID();
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		
		
		return loginID;
	}
	
	public static List<LoginWebsite> getAll() {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("FROM LoginWebsite");
		List<LoginWebsite> users = query.list();
	
		
		session.getTransaction().commit();
		
		
		
		return users;
	}
	public static List<LoginWebsite> getAllNotInTraining(int id) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("FROM LoginWebsite WHERE loginID NOT IN (SELECT compound.loginID FROM TrainingWerknemer WHERE trainingID = "+id+" and visibility=1)");
		List<LoginWebsite> users = query.list();
	
		
		session.getTransaction().commit();
		
		
		
		return users;
	}
	
	
	public static Boolean addLoginWebsiteWithoutPassword(String userName) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		LoginWebsite login = new LoginWebsite(userName);
		session.save(login);
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
	
		
		
		return true;
	}
	
	public static void main(String[] args) {
		
		Boolean bestaatUser = bestaatUserName("Seppe");
		System.out.println(bestaatUser);
		System.out.println(1);
		if(bestaatUser) {
			int loginID = getLoginIDFromUserName("Seppe");
		}
		else {
			Boolean toegevoegd = addLoginWebsiteWithoutPassword("Seppe");
		}
	}
	
}