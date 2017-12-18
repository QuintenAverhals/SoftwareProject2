package base;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

//***auteur: Chaimae*******
public class Employees {

	private String lastname;
	private String firstname;

	public Employees(String lastname, String firstname) {
		super();

		this.firstname = firstname;
		this.lastname = lastname;

	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public static String SubString1(String sub) {

		sub = sub.substring(103);
		sub.substring(0, sub.length() - 2);
		return sub;
	}

	public static Employees getEmployeeByID(int id) {
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://services.odata.org/V4/TripPinService/People('" + id + "')/FirstName");
		WebTarget target2 = client.target("http://services.odata.org/V4/TripPinService/People('" + id + "')/LastName");
		String firstname = target.request(MediaType.APPLICATION_JSON).get(String.class);
		firstname = Sub(firstname);
		String lastname = target2.request(MediaType.APPLICATION_JSON).get(String.class);
		lastname = Sub(lastname);

		Employees emp = new Employees(firstname, lastname);
		return emp;
	}

	public static String Sub(String sub) {

		sub = sub.substring(103);
		sub = sub.substring(0, sub.length()-2);
		return sub;
	}

	public static ArrayList<String> getAll1() {
		Client client = ClientBuilder.newClient();
		ArrayList<String> namen = new ArrayList<String>();
		for (int i = 1; i <= 9; i++) {
			WebTarget target = client
					.target("http://services.odata.org/V3/Northwind/Northwind.svc/Employees("+i+ ")/FirstName");
			String s = target.request(MediaType.APPLICATION_JSON).get(String.class);
			WebTarget target2 = client
					.target("http://services.odata.org/V3/Northwind/Northwind.svc/Employees("+i+")/LastName");
			String t = target2.request(MediaType.APPLICATION_JSON).get(String.class);
			s = Employees.Sub(s);
			t = Employees.Sub(t);
			String naam = s + " " + t;
			namen.add(naam);
		}
		return namen;
	}

	public static void main(String[] args) {
		
		
		ArrayList<String> lijst= getAll1();
		
		for (int i = 0; i < lijst.size(); i++) {
			
			System.out.println(lijst.get(i));
		}

	}

}
