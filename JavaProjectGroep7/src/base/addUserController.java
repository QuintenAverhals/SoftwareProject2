package base;

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

public class addUserController {
	public TextField CreateusernameTxt;
	public PasswordField CreatepasswordTxt;
	public PasswordField CreatepasswordComfirmTxt;
	public TextField CreateemailTxt;
	public CheckBox CreateAdminRights;
	public Button mainMenuBtn;
	
	public void mainMenu(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	
	public void logoutBtn(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("loginGui.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	public void goBack(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	public void addUser(ActionEvent event) throws Exception
	{
		
		
		String usrnm= CreateusernameTxt.getText();
		String pswd=  CreatepasswordTxt.getText();
		String pswdComfirm= CreatepasswordComfirmTxt.getText();
		String email=  CreateemailTxt.getText();
		Boolean admin= CreateAdminRights.isSelected();
		
		if(pswd.equals(pswdComfirm))
		{
			if((usrnm.equals(""))||(pswd.equals(""))||(pswdComfirm.equals(""))||(email.equals("")))
			{
				passwordNotSame("ERROR","all fields need to be fileld in");
				
			}else {
				Login nieweLogin= new Login();
				nieweLogin.createNewUser(usrnm, pswd, admin, email);
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
