package dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import base.Login;
import base.Main;
import base.SHA512;
import base.Logfile;

public class LogfileDAO {

	
	public static void addLogs(int userID, String action) throws Exception

	{
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		
		Logfile log= new Logfile(userID, action);
		
		System.out.println(log.toString());
		
		session.save(log);
		
		
		
		
		session.getTransaction().commit();
		
		
	}
	public static List<Logfile> getALL() throws Exception
	{
		
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction(); 
		Query query= session.createQuery("from Logfile");
		List<Logfile> logs= query.list();


		session.getTransaction().commit();
		return logs;
		
		
	}
	
}
