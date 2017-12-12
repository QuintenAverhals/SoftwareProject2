package base;
import base.Training;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import dao.LogfileDAO;

@Entity 
@Table(name="LOGFILE")

public class Logfile extends LogfileDAO {
	@Id
	@Column(name="logId")
	private int logId;
	@Column(name="date")
	private String Date;
	@Column(name="UserId")
	private int Userid;
	@Column(name="action")
	private String action;
	

	public Logfile() {
		
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public Logfile(int userid, String action) {
		super();
		Userid = userid;
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar calobj = Calendar.getInstance(); 
	     String a=df.format(calobj.getTime());
		this.Date= a; 
		this.action = action;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	
	
	public int getUserid() {
		return Userid;
	}
	public void setUserid(int userid) {
		Userid = userid;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
	public Date setDateTime(int date,int month,int year,int hour, int min, int sec) {
		Date myTime;
		Calendar cal = Calendar.getInstance();
	
		cal.set(Calendar.DATE,date);  
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.YEAR,year);
        
 
		cal.set(Calendar.HOUR_OF_DAY, hour);
	     cal.set(Calendar.MINUTE, min);
	     cal.set(Calendar.SECOND, sec);
	     cal.clear(Calendar.MILLISECOND);
	        myTime = cal.getTime();
	        return myTime;
	      
	      
	}
	@Override
	public String toString() {
		return "Logfile [logId=" + logId + ", Date=" + Date + ", Userid=" + Userid + ", action=" + action + "]";
	}



	

}
