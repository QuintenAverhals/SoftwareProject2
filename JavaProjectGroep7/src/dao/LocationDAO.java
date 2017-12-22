package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import base.LoginController;
import base.Location;
import base.Logfile;
import base.Main;

//auteur: chaimae

public class LocationDAO {

	public LocationDAO() {

	}

	public void updateBus(int id, String bus) throws Exception {
		// update
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Location location = new Location();

		location = (Location) session.get(Location.class, id);
		location.setBus(bus); //

		session.update(location);

		session.getTransaction().commit();
		Logfile log = new Logfile();
		LoginController currentUserr = new LoginController();
		int current = currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current,
				"User: " + currentUserr.getCurrentUser().getUsername() + " updated bus for location " + id);

	}

	public void updateCountry(int id, String country) throws Exception {
		// update
		Session session = Main.factory.getCurrentSession();

		session.beginTransaction();

		Location location = new Location();

		location = (Location) session.get(Location.class, id);
		location.setCountry(country); //

		session.update(location);

		session.getTransaction().commit();
		Logfile log = new Logfile();
		LoginController currentUserr = new LoginController();
		int current = currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current,
				"User: " + currentUserr.getCurrentUser().getUsername() + " updated country for location " + id);

	}

	public void updateCity(int id, String city) throws Exception {
		// update
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Location location = new Location();

		location = (Location) session.get(Location.class, id);
		location.setCity(city); //

		session.update(location);

		session.getTransaction().commit();
		Logfile log = new Logfile();
		LoginController currentUserr = new LoginController();
		int current = currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current,
				"User: " + currentUserr.getCurrentUser().getUsername() + " updated city for location " + id);

	}

	public void updateZipcode(int id, String zipcode) throws Exception {
		// update

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Location location = new Location();

		location = (Location) session.get(Location.class, id);
		location.setZip_code(zipcode); //

		session.update(location);

		session.getTransaction().commit();
		Logfile log = new Logfile();
		LoginController currentUserr = new LoginController();
		int current = currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current,
				"User: " + currentUserr.getCurrentUser().getUsername() + " updated zipcode for location " + id);

	}

	public void updateStreetname(int id, String streetname) throws Exception {
		// update
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Location location = new Location();

		location = (Location) session.get(Location.class, id);
		location.setStreetname(streetname);

		session.update(location);

		session.getTransaction().commit();
		Logfile log = new Logfile();
		LoginController currentUserr = new LoginController();
		int current = currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current,
				"User: " + currentUserr.getCurrentUser().getUsername() + " updated streetname for location " + id);

	}

	public void updateStreetnumber(int id, int streetnumber) throws Exception {
		// update
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Location location = new Location();

		location = (Location) session.get(Location.class, id);
		location.setStreetnumber(streetnumber);

		session.update(location);

		session.getTransaction().commit();
		Logfile log = new Logfile();
		LoginController currentUserr = new LoginController();
		int current = currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current,
				"User: " + currentUserr.getCurrentUser().getUsername() + " updated streetnumber for location  " + id);

	}

	public void createNewLocation(String city, String country, String zipcode, String bus, String streetname,
			int streetNumber) throws Exception {

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Location location = new Location(city, country, zipcode, bus, streetname, streetNumber);// remplir

		session.save(location);

		session.getTransaction().commit();

		Logfile log = new Logfile();
		LoginController currentUserr = new LoginController();
		int current = currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: " + currentUserr.getCurrentUser().getUsername() + " created location  " + streetname
				+ " " + streetNumber);

	}

	public static int createNewLocation(Location l) throws Exception {

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		int id = (int) session.save(l);

		session.getTransaction().commit();

		Logfile log = new Logfile();
		LoginController currentUserr = new LoginController();
		int current = currentUserr.getCurrentUser().getUser_ID();
		log.addLogs(current, "User: " + currentUserr.getCurrentUser().getUsername() + " created location  "
				+ l.getStreetname() + " " + l.getStreetnumber());

		return id;

	}

	public static List<Location> getAll() {
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Location location = new Location();
		Query query = session.createQuery("from Location");
		List<Location> locations = query.list();

		session.getTransaction().commit();

		return locations;

	}

<<<<<<< HEAD
	public static List<Location> getByID(int id) {
=======
	public List<Location> getByID(int id) {
>>>>>>> dev_Bilal
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Location location = new Location();
		Query query = session.createQuery("from Location where location_ID=" + id);
		List<Location> locations = query.list();

		session.getTransaction().commit();

		return locations;
	}

	public static int getID(Location l) {

		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();

		Query query = session.createQuery("from Location where bus='" + l.getBus() + "' and city= '" + l.getCity()
				+ "' and country='" + l.getCountry() + "' and streetname='" + l.getStreetname() + "' and streetnumber="
				+ l.getStreetnumber() + " and zipcode='" + l.getZip_code() + "'");
		List<Location> locations = query.list();

		session.getTransaction().commit();

		return locations.get(0).getLocation_ID();
	}

}
