package base;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class addSurveyController {
	@FXML
	public TextField newSurvey;
	@FXML
	public Button submit;
	public AnchorPane color;
	
	
	
	public void initialize() {
		
		String kleure= OptionsController.getColor();
					
		color.setStyle("-fx-background-color: #" + kleure);

	}

	@FXML
	public void submitQuestion(ActionEvent event) throws Exception
	{
		
			
			Survey.addSurvey(newSurvey.getText());
	
		submit.setDisable(true);
	}
}
