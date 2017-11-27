package dao;
import base.Training;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import SoftwareProject.Training;

public class TrainingDAO {

	public TrainingDAO() {
		
	}
	public void updateVisibility(int id, boolean invoer) {
		//update
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Training.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();

		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setVisibility(invoer);

		session.update(training);

		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
		}

	public void updateStartDate(int id, Date date) {
		//update
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Training.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();


		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setStart_date(date);



		session.update(training);

		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
		}
	
	public void updateEndDate(int id, Date end) {
		//update
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Training.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();


		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setEnd_date(end);



		session.update(training);

		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
		}

	public void updateStartTime(int id, Date startTime) {
		//update
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Training.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();


		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setStart_time(startTime);



		session.update(training);

		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
		}
	public void updateEndTime(int id, Date EndTime) {
		//update
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Training.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();


		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setEnd_time(EndTime);



		session.update(training);

		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
		}


	
	public Status updateStatus(int id, Status s) {
		//update
		SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Training.class).buildSessionFactory();
		Session session = sessionfactory.openSession();
		session.beginTransaction();


		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setStatus(s);



		session.update(training);

		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
		return s;
		}
public void createNewTraining(Date start_date,Date end_date,Date start_time,Date end_time,Status status,int surveyID,int locationID,boolean visibility) {
			
			SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Training.class).buildSessionFactory();
			Session session = sessionfactory.openSession();
			session.beginTransaction();
			
		Training training= new Training(start_date,end_date,start_time,end_time,status,surveyID,locationID, visibility);//remplir

		session.save(training);

		session.getTransaction().commit();
		session.close();
		sessionfactory.close();
			
		}

	

		public List<Training> getAll() {

			SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Training.class).buildSessionFactory();
			Session session = sessionfactory.openSession();
			session.beginTransaction();
			
			Training training=new Training();
			Query query = session.createQuery("from Training");
			List<Training> trainings= query.list();

			session.getTransaction().commit();
			session.close();
			sessionfactory.close();
			return trainings;
			
		}

		public List<Training> getByID(int id) {
			SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Training.class).buildSessionFactory();
			Session session = sessionfactory.openSession();
			session.beginTransaction();
			
			Training training=new Training();
			Query query = session.createQuery("from Training where training_ID="+ id);
			List<Training> trainings= query.list();
			
			session.getTransaction().commit();
			session.close();
			sessionfactory.close();
			return trainings;	
		}
		
}
