package dao;

import java.util.Date;

import base.LoginController;
import base.Logfile;
import base.Main;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import base.Training;
import base.Main;
import base.Status;

///******auteur chaimae*****
public class TrainingDAO {

	public TrainingDAO() {
		
	}
	public void updateVisibility(int id) throws Exception {
		//update
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setVisibility(false);

		session.update(training);

		session.getTransaction().commit();
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" deleted training "+id);
		
		}

	public void updateStartDate(int id, Date date) throws Exception {
		//update
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();


		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setStart_date(date);
		



		session.update(training);

		session.getTransaction().commit();
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated start date for training "+id);
		
		}
	
	public void updateEndDate(int id, Date end) throws Exception {
		//update
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();


		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setEnd_date(end);



		session.update(training);

		session.getTransaction().commit();
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated end date for training "+id);
		
		}

	public void updateStartTime(int id, Date startTime) throws Exception {
		//update
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();


		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setStart_time(startTime);



		session.update(training);

		session.getTransaction().commit();
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated start time for training "+id);
		
		}
	public void updateEndTime(int id, Date EndTime) throws Exception {
		//update
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();


		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setEnd_time(EndTime);



		session.update(training);

		session.getTransaction().commit();
		
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated end time for training "+id);
		
		}


	
<<<<<<< HEAD
	public Status updateStatus(int id, Status s) throws Exception {
=======
	public void updateCancel(int id) throws Exception {
>>>>>>> dev_Bilal
		//update
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();


		Training training= new Training();

		training = (Training) session.get(Training.class, id);
		training.setcancel(false);



		session.update(training);

		session.getTransaction().commit();
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
<<<<<<< HEAD
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated status for training "+id);
		
		return s;
=======
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated cancel for training "+id);
		
		
>>>>>>> dev_Bilal
		}
public void createNewTraining(Date start_date,Date end_date,Date start_time,Date end_time,int surveyID,int locationID,boolean visibility,String name) throws Exception {
			
	Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			
			
			
		Training training= new Training(start_date,end_date,start_time,end_time,surveyID,locationID, visibility,name);//remplir

		session.save(training);

		session.getTransaction().commit();
		
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" created Training "+name);
			
		}

	
<<<<<<< HEAD
public void updateALLTraining(int id, Date start_date,Date end_date,Date start_time,Date end_time,Status status,int surveyID,int locationID,boolean visibility, String trainingNaam) throws Exception {
=======
public void updateALLTraining(int id, Date start_date,Date end_date,Date start_time,Date end_time,int surveyID,int locationID,boolean visibility, String trainingNaam,boolean cancel) throws Exception {
>>>>>>> dev_Bilal
	
	Session session = Main.factory.getCurrentSession();
	session.beginTransaction();
	

Training training= new Training();

training = (Training) session.get(Training.class, id);
<<<<<<< HEAD
training.setEnd_time(end_date);
=======
training.setEnd_time(end_time);
>>>>>>> dev_Bilal
training.setEnd_date(end_date);
training.setLocationID(locationID);
training.setStart_date(start_date);
training.setStart_time(start_time);
<<<<<<< HEAD
training.setStatus(status);
=======
training.setcancel(cancel);
System.out.println(cancel);
>>>>>>> dev_Bilal
training.setSurveyID(surveyID);
training.setTrainingNaam(trainingNaam);



session.update(training);
session.save(training);

session.getTransaction().commit();
Logfile log= new Logfile();
LoginController currentUserr= new LoginController();
int current= currentUserr.getCurrentUser().getUser_ID();

log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated Training "+id);


}


public List<Training> getPastTrainings(){
	
	Session session = Main.factory.getCurrentSession();
	session.beginTransaction();
	
	Training training=new Training();
	Query query = session.createQuery("from Training where end_date < sysdate");
	List<Training> trainings= query.list();
	
	session.getTransaction().commit();
	

	return trainings;
	
	
	
}
		public List<Training> getAll() {

			Session session = Main.factory.getCurrentSession();
<<<<<<< HEAD
			session.beginTransaction();
			
			Training training=new Training();
			Query query = session.createQuery("from Training where visibility = 1");
			List<Training> trainings= query.list();

			session.getTransaction().commit();
			
			return trainings;
			
		}
		public List<Training> getAllPastTrainings() {

			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			
			Training training=new Training();
			Query query = session.createQuery("from Training where visibility = 1 and end_date> current_date()");
=======
			session.beginTransaction();
			
			Training training=new Training();
			Query query = session.createQuery("from Training where visibility = 1");
>>>>>>> dev_Bilal
			List<Training> trainings= query.list();

			session.getTransaction().commit();
			
			return trainings;
			
		}
		public List<Training> getAllPastTrainings() {

<<<<<<< HEAD
=======
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			
			Training training=new Training();
			Query query = session.createQuery("from Training where visibility = 1 and end_date> current_date()");
			List<Training> trainings= query.list();

			session.getTransaction().commit();
			
			return trainings;
			
		}

>>>>>>> dev_Bilal
		public Training getByID(int id) {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			
			Training training=new Training();
			Query query = session.createQuery("from Training where training_ID="+ id);
			training= (Training) query.uniqueResult();
			
			session.getTransaction().commit();
			
			return training;	
		}
		
		public static void main(String[] args) throws Exception {
			

Training st=new Training();

			Training tr=new Training();
			Date s=tr.setDate(15, 10,2023);
			Date end=tr.setDate(9, 10,2024);
			Date endTime= tr.setTime(19, 44, 00);
			Date startTime=tr.setTime(18,43, 00);
<<<<<<< HEAD
			Status ta=Status.CANCELLED;
			tr.createNewTraining(s, end, startTime, endTime, 4, 9, true,"ELLLOsdfdsfO");

			
=======
	
>>>>>>> dev_Bilal
			
			/*Training x=new Training();
			Date st=x.setDate(01, 05, 2015);
			Date en=x.setDate(10, 06, 2016);
			Date e= x.setTime(14, 10,00);
			Date sa=x.setTime(10, 30, 00);
		Status tat=Status.CANCELLED;
			x.createNewTraining(st, en, sa, e, tat, 4, 9, true,"cloud computing");

			
			
			Training y=new Training();
			Date a=y.setDate(24, 12, 2017);
			Date ae=y.setDate(20, 12, 2018);
			Date et= y.setTime(18, 44, 13);
			Date sttt=y.setTime(19, 06, 00);
		Status status=Status.CANCELLED;
			tr.createNewTraining(a, ae, sttt, et, status, 4, 9, true,"software engineering");

			
	Training aaa=new Training();
	Date baa=aaa.getStart_date();
	
	System.out.println(aaa.setDate(25, 12, 2000));

			*/
				
		}
		public Training startDatumByName(String naam) {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();	
			

			Training training=new Training();
			Query query = session.createQuery("from Training where TrainingNaam='"+ naam+"'");
			List<Training> trainings= query.list();
			
			

			
			
			session.getTransaction().commit();
			
			return trainings.get(0);
			
<<<<<<< HEAD
=======
		}
		
		
		public Training getByNaamDao(String naam) {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			
			Training training=new Training();
			Query query = session.createQuery("from Training where TrainingNaam='"+ naam+"'");
			training= (Training) query.list();
			
			session.getTransaction().commit();
			return training;
		}
		public List<Training> getTrainingsNaamDao(String naam) {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			
			Training training=new Training();
			Query query = session.createQuery("from Training where TrainingNaam='"+ naam+"'");
			List<Training> trainings= query.list();
			
			session.getTransaction().commit();
			
			return trainings;	
>>>>>>> dev_Bilal
		}
		
		
		public Training getByNaamDao(String naam) {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			
			Training training=new Training();
			Query query = session.createQuery("from Training where TrainingNaam='"+ naam+"'");
			training= (Training) query.list();
			
			session.getTransaction().commit();
			return training;
		}
		public List<Training> getTrainingsNaamDao(String naam) {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			
			Training training=new Training();
			Query query = session.createQuery("from Training where TrainingNaam='"+ naam+"'");
			List<Training> trainings= query.list();
			
			session.getTransaction().commit();
			
			return trainings;	
		}
}
