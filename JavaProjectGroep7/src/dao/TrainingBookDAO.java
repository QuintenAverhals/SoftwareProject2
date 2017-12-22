package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import base.AddBookController;
import base.Logfile;
import base.LoginController;
import base.Main;
import base.Statistics;
import base.TrainingBook;

public class TrainingBookDAO {
	public static void createTrainingBookLink(TrainingBook obj){
		
		if (isUnique(obj)) {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			
			session.save(obj);

			session.getTransaction().commit();

			Logfile log= new Logfile();
			LoginController currentUserr= new LoginController();
			int current= currentUserr.getCurrentUser().getUser_ID();
			try {
				log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" created new Training & Book link.");
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else {
			AddBookController.errorMsg("Error", "This book is already linked to this training!");
		}
	}
	
	private static boolean isUnique(TrainingBook obj) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		TypedQuery<TrainingBook> query = session.createQuery("FROM TrainingBook WHERE isbn='" + obj.getIsbn() + "' AND trainingID='" + obj.getTrainingID() + "'");
		List<TrainingBook> result= query.getResultList();
		session.getTransaction().commit();
		
		if (result.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}
}
