package base;



import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.Criteria;
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

	@Column(name="visibility")
	private boolean visibility;



	public Survey() {

	}

	public Survey(CompoundSurvey compoundSurveyKey, String question) {
		super();
		this.compoundSurveyKey = compoundSurveyKey;
		this.question = question;
		this.visibility=true;
	}


	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
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
	public static int getLastSurveyID() {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM Survey");
		List<Survey> survey = query.list();
		int last= survey.size();

		session.getTransaction().commit();



		return last;
	}
	public void setQuestion1(String question) {
		this.question = question;
	}

	//DEZE NOG CHECKEN
	public static void addSurvey(String question) throws Exception {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM Survey");
		List<Survey> survey = query.list();
		int last= survey.size();

		CompoundSurvey comp= new CompoundSurvey(last+1,1);
		Survey surveyy= new Survey(comp,question);


		session.save(surveyy);



		session.getTransaction().commit();

		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+"added survey "+surveyy.compoundSurveyKey.getSurvey_ID());




	}

	public static List<Survey> getAllSurveys() {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM Survey where visibility=1 ORDER BY survey_ID");
		List<Survey> surveys = query.list();


		session.getTransaction().commit();
		System.out.println("Statement Worked!");



		return surveys;
	}
	public static List<Integer> getAllSurveysIDS() {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();



		Criteria criteria = session.createCriteria( Survey.class );
		criteria.setProjection( Projections.distinct( Projections.property( "compoundSurveyKey.survey_ID" ) ) );
		List<Integer> surveys = criteria.list();

		session.getTransaction().commit();
		System.out.println("Statement Worked!");



		return surveys;
	}

	public static List<Survey> getQuestionsFromSurveyID(int surveyID){
		Session session = Main.factory.getCurrentSession();
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



		return survey;
	}

	public static List<Survey> getSpecificQuestionFromSurveyID(int surveyID, int questionID){
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM Survey WHERE survey_ID = "+surveyID +" AND question_ID = "+  questionID);
		List<Survey> survey = query.list();

		session.getTransaction().commit();
		System.out.println("Statement Worked!");



		return survey;
	}


	public static Boolean addQuestion(int surveyID, String question) throws Exception {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM Survey WHERE survey_ID = "+ surveyID);
		List<Survey> survey = query.list();

		int lengte = survey.size()+1;



		CompoundSurvey compkey= new CompoundSurvey(surveyID,lengte);
		Survey test= new Survey(compkey,question);

		session.save(test);


		session.getTransaction().commit();
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" added question "+question+" to survey"+surveyID);




		return true;
	}

	public static Boolean changeQuestion(int surveyID, int questionID, String newQuestion) throws Exception {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		List<Survey> vragen = getSpecificQuestionFromSurveyID(surveyID, questionID);
		Survey survey = vragen.get(0);
		survey.setQuestion(newQuestion);
		session.update(survey);

		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" changed question "+questionID+"from survey "+surveyID);



		return true;
	}
	public static void updateQuestion(Survey survey) throws Exception {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		session.update(survey);

		session.getTransaction().commit();
		System.out.println("Statement Worked!");
		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" updated question "+survey.getQuestion());



	}
	public static void deleteQuestion(Survey survey) throws Exception {

		if(survey!=null)
		{

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Survey s= (Survey) session.get(Survey.class, survey.getCompoundSurveyKey());
		s.setVisibility(false);

		session.update(s);


		session.getTransaction().commit();


		Logfile log= new Logfile();
		LoginController currentUserr= new LoginController();
		int current= currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" deleted question "+survey.getQuestion());

		}else {

		}


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
