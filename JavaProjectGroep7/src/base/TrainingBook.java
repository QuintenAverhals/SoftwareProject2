package base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.TrainingBookDAO;

@Entity 
@Table(name="TRAININGBOOK")

public class TrainingBook extends TrainingBookDAO{
	@Column(name="ISBN")
	private String isbn;
	@Id
	@Column(name="TrainingId")
	private int trainingID;
	
	
	public TrainingBook(String isbn, int trainingID) {
		super();
		this.isbn = isbn;
		this.trainingID = trainingID;
	}


	public TrainingBook() {
		super();
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public int getTrainingID() {
		return trainingID;
	}


	public void setTrainingID(int trainingID) {
		this.trainingID = trainingID;
	}
	
	
}
