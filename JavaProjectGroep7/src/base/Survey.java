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
	CompoundSurvey compoundSurveyKey;
	
	@Column(name="Question")
	private String question;
	
	
	
	public Survey() {
		
	}

	public Survey(CompoundSurvey compoundSurveyKey, String question) {
		super();
		this.compoundSurveyKey = compoundSurveyKey;
		this.question = question;
	}

	@Override
	public String toString() {
		return "Survey [compoundSurveyKey=" + compoundSurveyKey + ", question=" + question + "]";
	}

	public CompoundSurvey getCompoundSurveyKey() {
		return compoundSurveyKey;
	}

	public void setCompoundSurveyKey(CompoundSurvey compoundSurveyKey) {
		this.compoundSurveyKey = compoundSurveyKey;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	//DEZE NOG CHECKEN
	public static Survey getLastSurveyID() {
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Survey.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("FROM Survey ORDER BY compoundSurveyKey DESC");
		List<Survey> survey = query.list();
		Survey laatsteVraag = survey.get(0);
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return laatsteVraag;
	}
	
	public static List<Survey> getQuestionsFromSurveyID(int surveyID){
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Survey.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//CHECK DIT???
		Query query = session.createQuery("FROM Survey WHERE survey_ID = "+ surveyID);
		List<Survey> survey = query.list();
		for(int i=0;i<survey.size();i++)
		{
			System.out.println(survey.get(i).toString());
		}
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return survey;
	}

	public static List<Survey> getSpecificQuestionFromSurveyID(int surveyID, int questionID){
		SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Survey.class).configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM Survey WHERE survey_ID = "+surveyID +" AND question_ID = "+  questionID);
		List<Survey> survey = query.list();
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return survey;
	}
	
	public static void main(String[] args) {
		Survey test= new Survey();
		
		
		
	}
	
	public static Boolean addQuestion(int surveyID, String question) {
		SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Survey.class).configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Survey> vragen = getQuestionsFromSurveyID(surveyID);
		int lengte = vragen.size()+1;
		System.out.println(lengte);
		
		
		CompoundSurvey compkey= new CompoundSurvey(surveyID,lengte);
		Survey test= new Survey(compkey,question);
		System.out.println("1");
		session.save(test);
		System.out.println("2");
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionFactory.close();
		
		return true;
	}

	public static Boolean changeQuestion(int surveyID, int questionID, String newQuestion) {
		SessionFactory sessionFactory = new Configuration().addAnnotatedClass(Survey.class).configure().buildSessionFactory();
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


		//Boolean toegevoegd = addQuestion(5,"Was het eten lekker in het restaurant tijdens de avond?");
		//List<Survey> vragen= getQuestionsFromSurveyID(5);
		/*Boolean verander = changeQuestion(5,2,"Was het diner lekker?");
		List<Survey> vraag = getSpecificQuestionFromSurveyID(5, 2);
		System.out.println(vraag.get(0).getQuestion());
		System.out.println(vraag.get(0).getCompoundSurveyKey());
		*/
		/*Survey laatsteVraag = getLastSurveyID();
		System.out.println(laatsteVraag.getQuestion());
		System.out.println(laatsteVraag.getCompoundSurveyKey());
		*/
		
		/*
		for(int i=0; i < vragen.size(); i++) {
			System.out.println(vragen.get(i).getQuestion());
			System.out.println(vragen.get(i).getCompoundSurveyKey());
		}
		*/
		
		//maken van een niewe survey_question
		//OPLETTEN, voor dat je een instantie van survey maak moet je eerst een instantie van CompoundSurvey maken 
		//omdat deze nodig is voor de constructor
		/* SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Survey.class).buildSessionFactory();		
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		
		CompoundSurvey compkey= new CompoundSurvey(2,1);
		Survey test= new Survey(compkey,"this is a test2");
		
		session.save(test);
		
		
		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		session.close();
		sessionfactory.close();
		*/
	
		/*Survey test= new Survey();
		test.addQuestion(3, "DitIsSeppe3");
		List<Survey>surveyss = test.getQuestionsFromSurveyID(2);
		for(int i=0;i<surveyss.size();i++)
		{
			System.out.println(surveyss.get(i).toString());
		}*/
	
	

	}

