package base;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class addQuestionController {
	public Button submit;
	private Survey nieuweQuestion;
	
	public TextField newQuestion;
	
	
	
	public Survey getNieuweQuestion() {
		return nieuweQuestion;
	}



	public void setNieuweQuestion(Survey nieuweQuestion) {
		this.nieuweQuestion = nieuweQuestion;
	}



	public void submit(ActionEvent event) throws Exception
	{
		Survey.addQuestion(nieuweQuestion.getCompoundSurveyKey().getSurvey_ID(), newQuestion.getText());
		submit.setDisable(true);
	}
	
}
