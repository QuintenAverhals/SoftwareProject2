package base;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class addSurveyController {
	@FXML
	public TextField newSurvey;
	@FXML
	public Button submit;
	
	@FXML
	public void submitQuestion(ActionEvent event) throws Exception
	{
		
			
			Survey.addSurvey(newSurvey.getText());
	
		submit.setDisable(true);
	}
}
