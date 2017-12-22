package base;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
@Table(name="TRAININGPERWERKNEMER")
public class CompoundKeyTrainingWerknermer  implements Serializable{

	
	@Column(name="LoginID")
	private int loginID;
	@Column(name="TrainingID")
	private int trainingID;
	
	public CompoundKeyTrainingWerknermer() {
	}
	
	public CompoundKeyTrainingWerknermer(int loginID, int trainingID) {
		super();
		this.loginID = loginID;
		this.trainingID = trainingID;
	}
	public int getLoginID() {
		return loginID;
	}
	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}
	public int getTrainingID() {
		return trainingID;
	}
	public void setTrainingID(int trainingID) {
		this.trainingID = trainingID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + loginID;
		result = prime * result + trainingID;
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
		CompoundKeyTrainingWerknermer other = (CompoundKeyTrainingWerknermer) obj;
		if (loginID != other.loginID)
			return false;
		if (trainingID != other.trainingID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CompoundKeyTrainingWerknermer [loginID=" + loginID + ", trainingID=" + trainingID + "]";
	}
	
	
	
}
