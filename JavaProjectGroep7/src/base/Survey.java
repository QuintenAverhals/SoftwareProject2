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

@Entity
@Table(name="SURVEY_QUESTIONS")
public class Survey {
	@Id
	@Column(name="SurveyID")
	private int survey_ID;
	
	@Id
	@Column(name="QuestionID")
	private int question_ID;
	
	@Column(name="Question")
	private String question;
	
	public Survey(int survey_ID, int question_ID, String question) {
		super();
		this.survey_ID = survey_ID;
		this.question_ID = question_ID;
		this.question = question;
	}
	
	public Survey() {
		
	}

	public int getSurvey_ID() {
		return survey_ID;
	}

	public void setSurvey_ID(int survey_ID) {
		this.survey_ID = survey_ID;
	}

	public int getQuestion_ID() {
		return question_ID;
	}

	public void setQuestion_ID(int question_ID) {
		this.question_ID = question_ID;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	public List<Survey> getLastSurveyID() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("FROM SURVEY_QUESTIONS ORDER BY SurveyID DESC LIMIT 1");
		List<Survey> survey = query.list();
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return survey;
	}
	
	public List<Survey> getQuestionsFromSurveyID(int surveyID){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//CHECK DIT???
		Query query = session.createQuery("FROM SURVEY_QUESTIONS WHERE SurveyID = surveyID");
		List<Survey> survey = query.list();
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return survey;
	}
	
	public List<Survey> getSpecificQuestionFromSurveyID(int surveyID, int questionID){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//CHECK DIT???
		Query query = session.createQuery("FROM SURVEY_QUESTIONS WHERE SurveyID = surveyID AND QuestionID = questionID");
		List<Survey> survey = query.list();
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return survey;
	}
	
	public Boolean addQuestion(int surveyID, String question) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Survey> vragen = getQuestionsFromSurveyID(surveyID);
		int lengte = vragen.size();
		Survey survey = new Survey(surveyID, lengte+1, question);
		session.save(survey);
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return true;
	}
	
	public Boolean changeQuestion(int surveyID, int questionID, String newQuestion) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Survey> vragen = getSpecificQuestionFromSurveyID(surveyID, questionID);
		Survey survey = vragen.get(0);
		survey.setQuestion(newQuestion);
		session.update(survey);
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return true;
	}
	/*
	public String toString(int surveyID, int questionID) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Survey> vragen = getSpecificQuestionFromSurveyID(surveyID, questionID);
		String afdruk;
		
		for(int i = 0; i < vragen.size(); i++) {
			//afdruk += vragen
		}
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return afdruk;
	}
	*/
	
	public static void main(String[] args) {
		Survey test= new Survey(1,1,"test");
		test.addQuestion(1, "test");
		
	}
}