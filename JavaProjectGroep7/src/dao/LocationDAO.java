package dao;
import base.Location;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class LocationDAO {

public LocationDAO(){
	
	

}
public void updateBus(int id, String bus) {
//update
SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Location.class).buildSessionFactory();
Session session = sessionfactory.openSession();
session.beginTransaction();


Location location= new Location();

location = (Location) session.get(Location.class, id);
location.setBus(bus); //



session.update(location);

session.getTransaction().commit();
session.close();
sessionfactory.close();


}


public void updateCountry(int id, String country) {
	//update
	SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Location.class).buildSessionFactory();
	Session session = sessionfactory.openSession();
	session.beginTransaction();


	Location location= new Location();

	location = (Location) session.get(Location.class, id);
	location.setCountry(country); //



	session.update(location);

	session.getTransaction().commit();
	session.close();
	sessionfactory.close();


	}

public void updateCity(int id, String city) {
	//update
	SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Location.class).buildSessionFactory();
	Session session = sessionfactory.openSession();
	session.beginTransaction();


	Location location= new Location();

	location = (Location) session.get(Location.class, id);
	location.setCity(city); //



	session.update(location);

	session.getTransaction().commit();
	session.close();
	sessionfactory.close();


	}
public void updateZipcode(int id, String zipcode) {
	//update
	SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Location.class).buildSessionFactory();
	Session session = sessionfactory.openSession();
	session.beginTransaction();


	Location location= new Location();

	location = (Location) session.get(Location.class, id);
	location.setZip_code(zipcode); //



	session.update(location);

	session.getTransaction().commit();
	session.close();
	sessionfactory.close();


	}

public void updateStreetname(int id, String streetname) {
	//update
	SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Location.class).buildSessionFactory();
	Session session = sessionfactory.openSession();
	session.beginTransaction();


	Location location= new Location();

	location = (Location) session.get(Location.class, id);
	location.setStreetname(streetname);



	session.update(location);

	session.getTransaction().commit();
	session.close();
	sessionfactory.close();


	}

public void updateStreetnumber(int id, int streetnumber) {
	//update
	SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Location.class).buildSessionFactory();
	Session session = sessionfactory.openSession();
	session.beginTransaction();


	Location location= new Location();

	location = (Location) session.get(Location.class, id);
	location.setStreetnumber(streetnumber);



	session.update(location);

	session.getTransaction().commit();
	session.close();
	sessionfactory.close();


	}
public void createNewLocation(String city,String country, String zipcode, String bus,String streetname,int streetNumber) {
	
	SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Location.class).buildSessionFactory();
	Session session = sessionfactory.openSession();
	session.beginTransaction();
	

Location location= new Location(city,country,zipcode,bus,streetname,streetNumber);//remplir

session.save(location);

session.getTransaction().commit();
session.close();
sessionfactory.close();
	
}

public List<Location> getAll() {
	SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Location.class).buildSessionFactory();
	Session session = sessionfactory.openSession();
	session.beginTransaction();
	
	Location location=new Location();
	Query query = session.createQuery("from Location");
	List<Location> locations= query.list();

	session.getTransaction().commit();
	session.close();
	sessionfactory.close();
	return locations;
	
}

public List<Location> getByID(int id) {
	SessionFactory sessionfactory= new Configuration().configure().addAnnotatedClass(Location.class).buildSessionFactory();
	Session session = sessionfactory.openSession();
	session.beginTransaction();
	
	Location location=new Location();
	Query query = session.createQuery("from Location where location_ID="+ id);
	List<Location> locations= query.list();
	
	session.getTransaction().commit();
	session.close();
	sessionfactory.close();
	return locations;	
}


}
