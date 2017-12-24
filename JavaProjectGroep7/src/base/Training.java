package base;

import dao.TrainingDAO;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.TrainingDAO;

@Entity 
@Table(name="TRAINING")




public class Training extends TrainingDAO {
	
 
	
	@Id
	@Column(name="TrainingID")
	private int training_ID;
	@Column(name="start_date")
	private Date start_date;
	@Column(name="end_date")
	private Date end_date;
	@Column(name="start_time")
	private Date start_time;
	@Column(name="end_time")
	private Date end_time;
	@Column(name="status")
	private Status status;
	@Column(name="survey_id")
	private int SurveyID;
	@Column(name="location_id")
	private int locationID;
    @Column(name="visibility")
	private boolean visibility;
	


 
	public Training() {
		super();
	}
	
	
	
	public Training(java.util.Date start_date, java.util.Date end_date, java.util.Date start_time,
			java.util.Date end_time, Status status, int surveyID, int locationID, boolean visibility) {
		super();
		this.start_date = start_date;
		this.end_date = end_date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.status = status;
		SurveyID = surveyID;
		this.locationID = locationID;
		this.visibility = visibility;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	
	public int getTraining_ID() {
		return training_ID;
	}

	public void setTraining_ID(int training_ID) {
		this.training_ID = training_ID;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public int getSurveyID() {
		return SurveyID;
	}

	public void setSurveyID(int surveyID) {
		SurveyID = surveyID;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	@Override
	public String toString() {
		return "Training [training_ID=" + training_ID + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", status=" + status + ", locationID="
				+ locationID + ", visibility=" + visibility + "]";
	}
	
	
	public Date Date(int year, int month, int date) {
		Date myDate;

        Calendar cal = Calendar.getInstance();
       
        cal.set(Calendar.MONTH,month-1);
        cal.set(Calendar.DATE, date);
        cal.set(Calendar.YEAR, year);
  
        myDate = cal.getTime();
        
        return myDate;  
	
	}
	public Date time(int hour, int min, int sec) {
		Date myTime;
		Calendar cal = Calendar.getInstance();
		  
		
		cal.set(Calendar.HOUR_OF_DAY, hour);
	     cal.set(Calendar.MINUTE, min);
	     cal.set(Calendar.SECOND, sec);
	        
	        myTime = cal.getTime();
	        return myTime;
	      
	      
	}
	
	
}
