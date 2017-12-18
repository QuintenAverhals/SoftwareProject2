package base;

import javafx.scene.Node;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.List;

import dao.LoginDAO;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class LoginController {
	public Button loginBtn;
	public TextField usernameLogin;
	public PasswordField passwordLogin;
	public static Login currentUser;
	public AnchorPane color;
	public java.awt.Color kleur;
	
	
	
		

	public void initialize() {
	
			String kleure= OptionsController.getColor();
			System.out.println(kleure);			
			color.setStyle("-fx-background-color: #" + kleure);

		}
	
	
	
	
	



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
		int salt= LoginDAO.getSaltByUserName(usrn);
		String password=hasher.hashString(pswd+salt);
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
				
				Logfile log= new Logfile();
				LoginController currentUserr= new LoginController();
				int current= currentUserr.getCurrentUser().getUser_ID();
				log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" logged in.");
				
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenu.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);
			
			 Text l = (Text)passwordForgottenScene.lookup("#helloMSG");

			 Calendar c = Calendar.getInstance();
			 int Day = c.get(Calendar.HOUR_OF_DAY);
			 if(Day >= 0 && Day < 12) {
	         l.setText("good morning "+currentUser.getUsername());}
			 else if(Day>= 12 && Day < 16) {
				 l.setText("good afternoon "+currentUser.getUsername());}
			 else if(Day>= 16 && Day < 21) {
				 l.setText("good evening "+currentUser.getUsername());}
			 else if(Day >= 21 && Day < 24) {
				 l.setText("good night "+currentUser.getUsername());}
			window.show();
			}else {
				
				Logfile log= new Logfile();
				LoginController currentUserr= new LoginController();
				int current= currentUserr.getCurrentUser().getUser_ID();
				log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()+" logged in.");
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
			c.setHelloMSG("");*/
			
			
		}else {
			
			badLogin("Login Error","Bad username or password, try again");
		}
	}
	public void resetPassword(ActionEvent event) throws IOException, URISyntaxException {
		
		Desktop d= Desktop.getDesktop();
		d.browse(new URI("https://l.facebook.com/l.php?u=http%3A%2F%2Fwww.kaaimannen.be%2FSeppe%2Fwachtwoord_vergeten_applicatie.php&h=ATMDlcGeO0Zf_Lt6hjW2ytwPWmEYkLoIhzVzRRiz3-I8xruezwHvq4A5HqGpxIg2btf3n3Z3mzY3jOHh3UgROcVmiVWPcfGr3zvv2wAWhPnhh7m2pRTJrM4ohghmGg81z2s8GkXSsooyCA"));
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
