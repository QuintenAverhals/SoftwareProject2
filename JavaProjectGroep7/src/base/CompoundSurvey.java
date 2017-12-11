package base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

@Embeddable
@Table(name="SURVEY_QUESTIONS")
public class CompoundSurvey implements Serializable{
	
	@Column(name="survey_ID")
	private int survey_ID;
	private int question_ID;
	
	
	
	public CompoundSurvey() {
		super();
	}
	public CompoundSurvey(int survey_ID, int question_ID) {
		super();
		this.survey_ID = survey_ID;
		this.question_ID = question_ID;
	}
	@EmbeddedId
	public int getSurvey_ID() {
		return survey_ID;
	}
	public void setSurvey_ID(int survey_ID) {
		this.survey_ID = survey_ID;
	}
	
	public int getQuestion_ID() {
		return question_ID;
	}
	public void setQuestion_ID(int question_ID) {
		this.question_ID = question_ID;
	}
	
	@Override
	public String toString() {
		return "CompoundSurvey [survey_ID=" + survey_ID + ", question_ID=" + question_ID + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + question_ID;
		result = prime * result + survey_ID;
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
		CompoundSurvey other = (CompoundSurvey) obj;
		if (question_ID != other.question_ID)
			return false;
		if (survey_ID != other.survey_ID)
			return false;
		return true;
	}
	
	
}
