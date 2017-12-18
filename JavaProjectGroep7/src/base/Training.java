package base;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	@Column(name="cancel")
	private boolean cancel;
	@Column(name="survey_id")
	private int SurveyID;
	@Column(name="location_id")
	private int locationID;
    @Column(name="visibility")
	private boolean visibility;
    @Column(name="Training_Name")
    private String TrainingNaam;
	


 


	public String getTrainingNaam() {
		return TrainingNaam;
	}




	public void setTrainingNaam(String trainingNaam) {
		TrainingNaam = trainingNaam;
	}




	public Training() {
		super();
	}
	
	
	




	public Training( java.util.Date start_date, java.util.Date end_date, java.util.Date start_time,
			java.util.Date end_time, int surveyID, int locationID, boolean visibility,
			String trainingNaam) {
		super();
		this.start_date = start_date;
		this.end_date = end_date;
		this.start_time = start_time;
		this.end_time = end_time;
		SurveyID = surveyID;
		this.locationID = locationID;
		this.visibility = true;
		TrainingNaam = trainingNaam;
	}




	public boolean getcancel() {
		return cancel;
	}
	public void setcancel(boolean cancel) {
		this.cancel = cancel;
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

	public String toString() {
		return "Training [training_ID=" + training_ID + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", cancel=" + cancel + ", locationID="
				+ locationID + ", visibility=" + visibility + "]";
	}
	
	
	public Date setDate( int date, int month,int year) {
		Date myDate;

        Calendar cal = Calendar.getInstance();
     
        cal.set(Calendar.DATE, date);  
        cal.set(Calendar.MONTH,month-1);
        cal.set(Calendar.YEAR, year);
        
        cal.clear(Calendar.HOUR);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        myDate = cal.getTime();
        
        
       
        return myDate;  
	
	}
	public Date setTime(int hour, int min, int sec) {
		Date myTime;
		Calendar cal = Calendar.getInstance();
	
		cal.clear(Calendar.DATE);  
        cal.clear(Calendar.MONTH);
        cal.clear(Calendar.YEAR);
        
 
		cal.set(Calendar.HOUR_OF_DAY, hour);
	     cal.set(Calendar.MINUTE, min);
	     cal.set(Calendar.SECOND, sec);
	     cal.clear(Calendar.MILLISECOND);
	        myTime = cal.getTime();
	        return myTime;
	      
	      
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + SurveyID;
		result = prime * result + ((TrainingNaam == null) ? 0 : TrainingNaam.hashCode());
		result = prime * result + ((end_date == null) ? 0 : end_date.hashCode());
		result = prime * result + ((end_time == null) ? 0 : end_time.hashCode());
		result = prime * result + locationID;
		result = prime * result + ((start_date == null) ? 0 : start_date.hashCode());
		result = prime * result + ((start_time == null) ? 0 : start_time.hashCode());
		
		result = prime * result + training_ID;
		result = prime * result + (visibility ? 1231 : 1237);
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
		Training other = (Training) obj;
		if (SurveyID != other.SurveyID)
			return false;
		if (TrainingNaam == null) {
			if (other.TrainingNaam != null)
				return false;
		} else if (!TrainingNaam.equals(other.TrainingNaam))
			return false;
		if (end_date == null) {
			if (other.end_date != null)
				return false;
		} else if (!end_date.equals(other.end_date))
			return false;
		if (end_time == null) {
			if (other.end_time != null)
				return false;
		} else if (!end_time.equals(other.end_time))
			return false;
		if (locationID != other.locationID)
			return false;
		if (start_date == null) {
			if (other.start_date != null)
				return false;
		} else if (!start_date.equals(other.start_date))
			return false;
		if (start_time == null) {
			if (other.start_time != null)
				return false;
		} else if (!start_time.equals(other.start_time))
			return false;
		if (cancel != other.cancel)
			return false;
		if (training_ID != other.training_ID)
			return false;
		if (visibility != other.visibility)
			return false;
		return true;
	}









	




	






	
	
	
}
