package base;
import dao.LocationDAO;
import org.hibernate.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id; 
import javax.persistence.Table;
import java.util.List;  
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory; 
import org.hibernate.cfg.Configuration;
import dao.LocationDAO;

@Entity 
@Table(name="LOCATION")
public class Location extends LocationDAO{
	
@Id
@Column(name="locationID")
private int location_ID;

@Column(name="city")
private String city;

@Column(name="country")
private String country;

@Column(name="zipcode")
private String zip_code;

@Column(name="bus")
private String bus;

@Column(name="streetName")
private String streetname;

@Column(name="streetnumber")
private int streetnumber;

public Location(String city, String country, String zip_code, String bus,String streetname, int streetnumber) {
	super();
	
	this.city = city;
	this.country = country;
	this.zip_code = zip_code;
	this.bus = bus;
	this.streetname=streetname;
	this.streetnumber = streetnumber;
}



public Location() {
	super();
}

public int getLocation_ID() {
	return location_ID;
}
public void setLocation_ID(int location_ID) {
	this.location_ID = location_ID;
}

public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getZip_code() {
	return zip_code;
}
public void setZip_code(String zip_code) {
	this.zip_code = zip_code;
}
public String getBus() {
	return bus;
}
public void setBus(String bus) {
	this.bus = bus;
}


public String getStreetname() {
	return streetname;
}
public void setStreetname(String streetname) {
	this.streetname = streetname;
}



public int getStreetnumber() {
	return streetnumber;
}
public void setStreetnumber(int streetnumber) {
	this.streetnumber = streetnumber;
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((bus == null) ? 0 : bus.hashCode());
	result = prime * result + ((city == null) ? 0 : city.hashCode());
	result = prime * result + ((country == null) ? 0 : country.hashCode());
	result = prime * result + location_ID;
	result = prime * result + ((streetname == null) ? 0 : streetname.hashCode());
	result = prime * result + streetnumber;
	result = prime * result + ((zip_code == null) ? 0 : zip_code.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Location other = (Location) obj;
	if (bus == null) {
		if (other.bus != null)
			return false;
	} else if (!bus.equals(other.bus))
		return false;
	if (city == null) {
		if (other.city != null)
			return false;
	} else if (!city.equals(other.city))
		return false;
	if (country == null) {
		if (other.country != null)
			return false;
	} else if (!country.equals(other.country))
		return false;
	if (location_ID != other.location_ID)
		return false;
	if (streetname == null) {
		if (other.streetname != null)
			return false;
	} else if (!streetname.equals(other.streetname))
		return false;
	if (streetnumber != other.streetnumber)
		return false;
	if (zip_code == null) {
		if (other.zip_code != null)
			return false;
	} else if (!zip_code.equals(other.zip_code))
		return false;
	return true;
}
@Override
public String toString() {
	return "Location [location_ID=" + location_ID + ", city=" + city + ", country=" + country + ", zip_code=" + zip_code
			+ ", bus=" + bus + ", streetname=" + streetname + ", streetnumber=" + streetnumber + "]";
}


public static void main(String[] args) {
	Location location= new Location();
	
	location.updateBus(9, "yo1lo");

	//todo : update all var
	

}
}
