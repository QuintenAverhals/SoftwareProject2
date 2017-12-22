package base;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.jsonb.JsonBindingFeature;

import com.mysql.fabric.Response;


  //auteur: chaimae
public class oData{
//username toevoegen
	public oData() {
		
	}

		
	public static String getEmail(Client client, String id) {
		 client = ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V4/TripPinService/People('"+id+"')/Emails?$format=application/json;odata.metadata=none");
		String s=target.request(MediaType.APPLICATION_JSON).get(String.class);

		return s;
	}

	public static String getFirstName(Client client, String id) {
		 client = ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V4/TripPinService/People('"+id+"')/FirstName?$format=application/json;odata.metadata=none");
		String s=target.request(MediaType.APPLICATION_JSON).get(String.class);
		
		return s;
	}
	
	public static String getLastName(Client client, String id) {
		 client = ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V4/TripPinService/People('"+id+"')/LastName?$format=application/json;odata.metadata=none");
		String s=	target.request(MediaType.APPLICATION_JSON).get(String.class);
		return s;
	}
	
	public static String getAddress(Client client, String id) {
		 client = ClientBuilder.newClient();
		WebTarget target = client.target("http://services.odata.org/V4/TripPinService/People('"+id+"')/AddressInfo?$format=application/json;odata.metadata=none");
		String s=	target.request(MediaType.APPLICATION_JSON).get(String.class);
	return s;
		
	}
	
	
	
	//reminder email
	//username toevoegen aan array
	//array van usernames om te data per employees te overlopen
	
	private static void UsernameArray()
	{ 

	   String[] names  = new String[7];

	    names[0] = "scottketchum";
	    names[1] = "russellwhyte";
	    names[2] = "ronaldmundy";
	    names[3] = "javieralfred";
	    names[4] = "willieashmore";
	    names[5] = "vincentcalabrese";
	    names[6]="clydeguess";
	    
	 
	    for (String element:names) {
	    
	    	Client client = ClientBuilder.newClient();
	    	String met=getLastName(client,element);
	    	String sub=met.substring(10);
	    	sub=sub.substring(0, sub.length()-2);
			System.out.println(sub);
			
			String metho=getFirstName(client,element);
	    	String subs=metho.substring(10);
	    	subs=subs.substring(0, subs.length()-2);
			System.out.println(subs);
			
			String methode=getEmail(client,element);
	    	String submethode=methode.substring(10);
	    	submethode=submethode.substring(0, submethode.length()-2);
			System.out.println(submethode);
			
			String m=getAddress(client,element);
	    	String s=m.substring(10);
	    	s=s.substring(0, s.length()-2);
			System.out.println(s);
			System.out.println("================");
		}
	}
	
public  static void main(String[]args) {
	Client client = ClientBuilder.newClient();
	
	/*String met=getLastName(client,"scottketchum");
	String sub=met.substring(10);
	sub=sub.substring(0, sub.length()-2);
	System.out.println(sub);
	
	String metho=getFirstName(client,"scottketchum");
	String subs=metho.substring(10);
	subs=subs.substring(0, subs.length()-2);
	System.out.println(subs);
	
	String methode=getEmail(client,"scottketchum");
	String submethode=methode.substring(10);
	submethode=submethode.substring(0, submethode.length()-2);
	System.out.println(submethode);
	
	String m=getAddress(client,"scottketchum");
	String s=m.substring(10);
	s=s.substring(0, s.length()-2);
	System.out.println(s);*/
	
	UsernameArray();
	
	
}
}
