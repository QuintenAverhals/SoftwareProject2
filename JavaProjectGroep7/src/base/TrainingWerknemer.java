package base;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;
import org.hibernate.Session;

import base.LoginWebsite;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

@Entity
@Table(name="TRAININGPERWERKNEMER")
public class TrainingWerknemer {
	@Id
	CompoundKeyTrainingWerknermer compound;
	
	
	
	
	public CompoundKeyTrainingWerknermer getCompound() {
		return compound;
	}

	public void setCompound(CompoundKeyTrainingWerknermer compound) {
		this.compound = compound;
	}

	public TrainingWerknemer() {
		super();
	}

	public void mainMenu(ActionEvent event) throws Exception
	{
		LoginController current= new LoginController();
		Login currentUser= current.getCurrentUser();
		
		
		if(currentUser.isAdmin()==true)
		{
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenu.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);
			
			window.show();
		}else {
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenuNormaleUser.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);
			
			window.show();
			
		}
	}
	
	/*
	public static Boolean addTrainingForEmployee(int loginID, int trainingID) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		TrainingWerknemer trainingWerknemer = new TrainingWerknemer(loginID, trainingID);
		session.save(trainingWerknemer);
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		
		
		return true;
	}
	*/
	
	

	@Override
	public String toString() {
		return "TrainingWerknemer [compound=" + compound + ", getCompound()=" + getCompound() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public static TrainingWerknemer getTrainingWerknemer(int loginID, int trainingID) {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();		
				
		Query query = session.createQuery("FROM TrainingWerknemer WHERE loginID = "+loginID + " AND trainingID = "+trainingID);
		List<TrainingWerknemer> trainingen = query.list();
		TrainingWerknemer training = trainingen.get(0);
		
		for(int i=0;i<trainingen.size();i++)
		{
			System.out.println(trainingen.get(i).toString());
		}		
		
		session.getTransaction().commit();
			
		
		return training;
	}
	public static List<TrainingWerknemer> getALL() {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();		
				
		Query query = session.createQuery("FROM TrainingWerknemer");
		List<TrainingWerknemer> trainingen = query.list();

		
		
		session.getTransaction().commit();
		
		
		return trainingen;
	}
	
	
	public static void DeleteFromTraining(CompoundKeyTrainingWerknermer id) throws Exception {
		Session session = Main.factory.getCurrentSession();	
		session.beginTransaction();		
		
		Query query = session.createSQLQuery("Delete from TRAININGPERWERKNEMER where LoginID="+id.getLoginID()+" and TrainingID="+id.getTrainingID());
		query.executeUpdate();
		session.getTransaction().commit();
		System.out.println(id.toString());
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" removed user "+id.getLoginID()+" from training "+id.getTrainingID());

	}
	public static void addToTraining(CompoundKeyTrainingWerknermer id) throws Exception {
		Session session = Main.factory.getCurrentSession();	
		session.beginTransaction();				
			
			
			Query query = session.createSQLQuery("Insert into TRAININGPERWERKNEMER (LoginID,TrainingID) values ("+id.getLoginID()+","+id.getTrainingID()+")");
			int a = query.executeUpdate();
			System.out.println(a);
			
			System.out.println(id.toString());

		session.getTransaction().commit();

		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" Added user: "+id.getLoginID()+" to training "+id.getTrainingID());
	}
	
	
	public static void main(String[] args) {
			
	}	
}
