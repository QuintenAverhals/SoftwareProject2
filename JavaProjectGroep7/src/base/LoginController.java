package base;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.List;

import java.awt.Color;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

public class LoginController {
	public Button loginBtn;
	public TextField usernameLogin;
	public PasswordField passwordLogin;
	public static Login currentUser;
	
	public Login getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Login currentUser) {
		this.currentUser = currentUser;
	}

	public void handleButtonClick(ActionEvent event) throws Exception
	{
		String usrn= usernameLogin.getText();
		String pswd= passwordLogin.getText();
		currentUser= new Login(usrn, pswd);
		List<Login> users= currentUser.getUsersByName(usrn);
		boolean result= currentUser.login(usrn, pswd);
		SHA512 hasher = new SHA512();
		String password=hasher.hashString(pswd);
		for(int i=0;i<users.size();i++)
		{
			if(users.get(i).getUsername().equals(usrn)&& users.get(i).getPassword().equals(password))
			{
				currentUser= users.get(i);
			}
		}
		if(result == true)
		{
			if(currentUser.isAdmin()==true)
			{
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenu.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);
			
			 Text l = (Text)passwordForgottenScene.lookup("#helloMSG");
	         l.setText("good morning "+currentUser.getUsername());
			
			
			window.show();
			}else {
				Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenuNormaleUser.fxml"));
				Scene passwordForgottenScene = new Scene(passwordForgottenParent);
				
				Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
				window.setScene(passwordForgottenScene);
				
				 Text l = (Text)passwordForgottenScene.lookup("#helloMSG");
		         l.setText("good morning "+currentUser.getUsername());
				
				
				window.show();
			}
			
				
			/*FXMLLoader f = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
			LoginGui.setRoot(f.load());
			mainMenuController c = f.<mainMenuController>getController();
			c.setHelloMSG("ZBEUB");*/
			
			
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
		label.setStyle("-fx-text-fill: #757575;");
		Button closeButton= new Button("Close the window");
		closeButton.setOnAction(e -> window.close());
		closeButton.setStyle("-fx-background-color: grey; -fx-text-fill: WHITE;");
		
		VBox layout= new VBox(10);
		layout.getChildren().add(label);
		layout.getChildren().add(closeButton);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-background-color: #2d3440;");
		
		
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}

	


}
