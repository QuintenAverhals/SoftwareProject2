package base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddUserController {
	public TextField CreateusernameTxt;
	public PasswordField CreatepasswordTxt;
	public PasswordField CreatepasswordComfirmTxt;
	public TextField CreateemailTxt;
	public CheckBox CreateAdminRights;
	
	
	private Pattern mailChecker = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	
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
		LoginController current= new LoginController();
		Login currentUser= current.getCurrentUser();
		
		
		if(currentUser.isAdmin()==true)
		{
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/userMenu.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);
			
			window.show();
		}else {
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenuNormaleUser.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);
			
			window.show();
			
		}
	}
	public void addUser(ActionEvent event) throws Exception
	{
		
		
		String usrnm= CreateusernameTxt.getText();
		String pswd=  CreatepasswordTxt.getText();
		String pswdComfirm= CreatepasswordComfirmTxt.getText();
		String email=  CreateemailTxt.getText();
		Boolean admin= CreateAdminRights.isSelected();
		Login check= new Login();
		boolean checkName= check.checkUsernameUnique(usrnm); 
		
		if(pswd.equals(pswdComfirm))
		{
			if((usrnm.equals(""))||(pswd.equals(""))||(pswdComfirm.equals(""))||(email.equals("")))
			{
				passwordNotSame("ERROR","all fields need to be filled in");
				
			}else {
				if(checkName==false) {
					//CAREFULL!! EMAIL CHECKER DOESNT work 100% if you give letters with accents it will fail!
					Matcher emailChecker= mailChecker.matcher(email);
					if(emailChecker.find()){

						Login nieweLogin= new Login();
						nieweLogin.createNewUser(usrnm, pswd, admin, email);
						passwordNotSame("SUCCESS", "User has been added successfully");
					}else {
						passwordNotSame("EMAIL ERROR", "THIS IS NOT A VALID EMAIL! TRY AGAIN");
					}
			
					
				}else
					//could be better with function that checks if suggested username not already taken as well but not enough time
				passwordNotSame("NOT UNIQUE","This userName is already Taken! you could try "+usrnm+usrnm.substring(0, 1).toUpperCase());
			}
			
		}else{
			passwordNotSame("INPUT ERROR","passwords are not the same! try again");
		}
	
		
		
	}
	
	public static void passwordNotSame(String title, String msg)
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
