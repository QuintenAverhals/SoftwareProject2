package base;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.LoginDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class UserOverviewController {
	public ListView viewList;
	public TextField userID;
	public TextField userName;
	public TextField filterVieww;
	public PasswordField Password;
	public TextField email;
	public CheckBox isAdmin;
	public List<Login> users;
	public GridPane color;
	private String currentName;
	
	private Pattern mailChecker = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	@FXML
	public void initialize()
	{
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Login user= new Login();
		
		users= user.getALL();
	
		
		for(int i=0;i<users.size();i++)
		{
			
			viewList.getItems().addAll(users.get(i).getUser_ID()+": "+users.get(i).getUsername());
		
		}
		String kleure= OptionsController.getColor();
		
		color.setStyle("-fx-background-color: #" + kleure);
	} 
	
	public void mainMenu(ActionEvent event) throws Exception
	{
		LoginController current= new LoginController();
		Login currentUser= current.getCurrentUser();
		
		
		if(currentUser.isAdmin()==true)
		{
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenu.fxml"));
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
	
	public void fillBlanks(MouseEvent arg0) throws Exception
	{
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		String selected = (String) viewList.getSelectionModel().getSelectedItem();
		selected = selected.replaceAll("[a-zA-Z]", "");
		selected = selected.replaceAll(":", "");
		Login selLogin= new Login();
		currentName= selLogin.getUsername();
	
		
	
		selLogin = selLogin.getUsersByID(selected);
		Password.setText("******");
		email.setText(selLogin.getEmail());
		userName.setText(selLogin.getUsername());
		isAdmin.setSelected(selLogin.isAdmin());
		String id = ""+ selLogin.getUser_ID();
		userID.setText(id);
		
		
	}
	public void logoutBtn(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/LoginMenu.fxml"));
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
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/userMenu.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);
			
			window.show();
			
		}
	}
	public void updateBtn(ActionEvent event) throws Exception
	{
		String password= Password.getText();
		String mail= email.getText();
		String username= userName.getText();
		boolean admin = isAdmin.isSelected();
		String idString= userID.getText();
		int id=0;
	
		try {
			id= Integer.parseInt(idString);
			
		}catch(NumberFormatException e)
		{
			passwordNotSame("Error", "You must select an Item before Updating!");
			
		}
		if(id!=0)
		{
			
		
		
			
		Login check= new Login();
		boolean checkName= check.checkUsernameUnique(username); 
		
		if((username.equals(""))||(password.equals(""))||(mail.equals("")))
		{
			passwordNotSame("ERROR","all fields need to be filled in");
			
		}else { 
				//CAREFULL!! EMAIL CHECKER DOESNT work 100% if you give letters with accents it will fail!
				Matcher emailChecker= mailChecker.matcher(mail);
				if(emailChecker.find()){
					Login nieweLogin= new Login();
					check.updateAll(id, email.getText(), username, password, admin);
					passwordNotSame("SUCCESS", "User has been successfully Updated");
				}else {
					passwordNotSame("EMAIL ERROR", "THIS IS NOT A VALID EMAIL! TRY AGAIN");
				}
			}
		}
		viewList.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Login user= new Login();
		
		users= user.getALL();
	
		
		for(int i=0;i<users.size();i++)
		{
			
			viewList.getItems().addAll(users.get(i).getUser_ID()+": "+users.get(i).getUsername());
		
		}
		
		}
	
	
	public void deleteBtn(ActionEvent event) throws Exception 
	{
		
		
		
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		String selected = (String) viewList.getSelectionModel().getSelectedItem();
		if(selected==null)
		{
			passwordNotSame("Error", "You must select An item first!");
			
		}else {
			
		selected = selected.split(":")[0];
		int id= Integer.parseInt(selected);
		
		
		Login user= new Login();
		
		LoginDAO.deleteLogin(id);
		
		viewList.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		
		users= user.getALL();
	
		
		for(int i=0;i<users.size();i++)
		{
			
			viewList.getItems().addAll(users.get(i).getUser_ID()+": "+users.get(i).getUsername());
		
		}
		}
	}
	public void Load_Content(ActionEvent event) throws Exception
	{

		viewList.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Login user= new Login();
		
		users= user.getALL();
	
		
		for(int i=0;i<users.size();i++)
		{
			
			viewList.getItems().addAll(users.get(i).getUser_ID()+": "+users.get(i).getUsername());
		
		}
		
	
	}
	public void filterView(ActionEvent event) throws Exception
	{
		
		viewList.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Login user= new Login();
		String searchUser= filterVieww.getText();
		
		if(searchUser.equals(""))
		{
			
			viewList.getItems().clear();
			viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			
			
			users= user.getALL();
		
			
			for(int i=0;i<users.size();i++)
			{
				
				viewList.getItems().addAll(users.get(i).getUser_ID()+": "+users.get(i).getUsername());
			
			}
		}else {
			
			for(int i=0;i<users.size();i++)
			{
				if(users.get(i).getUsername().contains(searchUser))
				
				viewList.getItems().addAll(users.get(i).getUser_ID()+": "+users.get(i).getUsername());	
		}
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
