package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import base.Main;
import base.Statistics;

public class StatisticsDAO {



	public static ArrayList<Statistics> getTopCertificates() {
		ArrayList<Statistics> stats = new ArrayList<Statistics>();
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		
		int counter = 1;

		String sql = "SELECT employee_name, COUNT(employee_name) AS 'Aantal' FROM CERTIFICATE GROUP BY employee_name ORDER BY Aantal DESC LIMIT 5";
		SQLQuery q = session.createSQLQuery(sql);
		q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List l = q.list();


		for (Object obj : l) {
			int amountFirst = obj.toString().indexOf("Aantal") + 7;
			int amountLast = obj.toString().indexOf(",");

			int nameFirst = obj.toString().indexOf("name") + 5;
			int nameLast = obj.toString().indexOf("}");

			stats.add(new Statistics(counter + ") " + obj.toString().substring(nameFirst, nameLast), Integer.parseInt(obj.toString().substring(amountFirst, amountLast))));
			counter++;
		}

		session.getTransaction().commit();
		
		return stats;
	}
	
	public static ArrayList<Statistics> getTopTrainings() {
		ArrayList<Statistics> stats = new ArrayList<Statistics>();
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		int counter = 1;
		
		String sql = "SELECT WebsiteUserName, COUNT(Training_Name) AS 'Aantal' FROM TRAININGPERWERKNEMER tw JOIN TRAINING t ON (tw.TrainingID= t.TrainingID) JOIN LOGIN_WEBSITE l ON (tw.LoginID = l.LoginID) WHERE YEAR(start_date) = YEAR(CURRENT_DATE()) GROUP BY WebsiteUserName ORDER BY Aantal DESC LIMIT 5";
		SQLQuery q = session.createSQLQuery(sql);
		q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List l = q.list();
		
		
		
		for (Object obj : l) {
			int nameFirst = obj.toString().indexOf("Name") + 5;
			int nameLast = obj.toString().indexOf(",");
			
			int amountFirst = obj.toString().indexOf("Aantal") + 7;
			int amountLast = obj.toString().indexOf("}");

			stats.add(new Statistics(counter + ") " + obj.toString().substring(nameFirst, nameLast), Integer.parseInt(obj.toString().substring(amountFirst, amountLast))));
			counter++;
		}

		session.getTransaction().commit();
		
		return stats;
	}
}
