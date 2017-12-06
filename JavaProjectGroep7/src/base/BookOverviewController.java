package base;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookOverviewController {

	public void mainMenu(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	
	public void logoutBtn(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/loginMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	
	public void goBack(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/bookMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
}