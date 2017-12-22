package base;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateSurveyController {
	private Survey nieuweQuestion;
	@FXML public Button submit;
	
	
	@FXML
	public TextField question;
	
	
	public void setQuestion(Survey survey)
	{
		nieuweQuestion= survey;
		question.setText(nieuweQuestion.getQuestion());
		
	}
	@FXML
	public void submitQuestion(ActionEvent event) throws Exception
	{
		nieuweQuestion.setQuestion(question.getText());
		Survey.updateQuestion(nieuweQuestion);
		submit.setDisable(true);
	}
	
	
	
}
