package base;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class addQuestionController {
	public Button submit;
	private Survey nieuweQuestion;

	public TextField newQuestion;
	public AnchorPane color;


	public Survey getNieuweQuestion() {
		return nieuweQuestion;
	}



	public void setNieuweQuestion(Survey nieuweQuestion) {
		this.nieuweQuestion = nieuweQuestion;
	}

	public void initialize() {

		String kleure= OptionsController.getColor();
		color.setStyle("-fx-background-color: #" + kleure);

	}


	public void submit(ActionEvent event) throws Exception
	{
		try {

			Survey.addQuestion(nieuweQuestion.getCompoundSurveyKey().getSurvey_ID(), newQuestion.getText());
			submit.setDisable(true);
		}catch(NullPointerException e)
		{

		}
	}

}
