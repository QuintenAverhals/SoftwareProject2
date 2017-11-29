package base;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

public class Controller {
	public Button loginBtn;
	public TextField usernameLogin;
	public PasswordField passwordLogin;
	public Login currentUser;
	public void handleButtonClick(ActionEvent event) throws Exception
	{
		String usrn= usernameLogin.getText();
		String pswd= passwordLogin.getText();
		currentUser= new Login(usrn, pswd);
		boolean result= currentUser.login(usrn, pswd);
		if(result == true)
		{
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);
			
			window.show();
			
			
		}else {
			
			badLogin("Login Error","Bad username or password, try again");
		}
	}
	
	public static void badLogin(String title, String msg)
	{
		Stage window= new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label= new Label();
		
		label.setText(msg);
		Button closeButton= new Button("Close the window");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout= new VBox(10);
		layout.getChildren().add(label);
		layout.getChildren().add(closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}
	
	


}
