package base;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class mainMenuController extends Controller {

	Button logoutBtn;
	Button TrainingBtn;
	Button StatisticsBTN;
	Button optionBtn;
	Button ManagementBtn;
	Button bookBtn;
	Button employeeBtn;
	Text helloMSG;
	
	public void logoutBtn(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("loginGui.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	public void management(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("userMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	
	public void book(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("bookMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	
	public void training(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("trainingMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
}
