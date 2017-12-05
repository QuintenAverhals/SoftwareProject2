package base;

import java.util.List;

import dao.LoginDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserOverviewController {
<<<<<<< HEAD
	public ListView viewList;
	public TextField userID;
	public TextField userName;
	public TextField filterVieww;
	public PasswordField Password;
	public TextField email;
	public CheckBox isAdmin;
	public List<Login> users;
	
	@FXML
	public void initialize()
	{
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Login user= new Login();
		
		users= user.getALL();
=======

	public void mainMenu(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
>>>>>>> dev_Tom
	
		
		for(int i=0;i<users.size();i++)
		{
			
			viewList.getItems().addAll(users.get(i).getUser_ID()+": "+users.get(i).getUsername());
		
		}
	}
	
	
	
	public void fillBlanks(ActionEvent event) throws Exception
	{
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		String selected = (String) viewList.getSelectionModel().getSelectedItem();
		selected = selected.replaceAll("[a-zA-Z]", "");
		selected = selected.replaceAll(":", "");
		System.out.println(selected);
		
	
		Login selLogin= new Login();
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
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/loginGui.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	public void goBack(ActionEvent event) throws Exception
	{
		Controller current= new Controller();
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
		int id= Integer.parseInt(idString);
		
		
		Login login= new Login();
		login.updateAll(id, mail, username, password, admin);
		

		viewList.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Login user= new Login();
		
		users= user.getALL();
	
		
		for(int i=0;i<users.size();i++)
		{
			
			viewList.getItems().addAll(users.get(i).getUser_ID()+": "+users.get(i).getUsername());
		
		}
		
		
	}
	public void deleteBtn(ActionEvent event) 
	{
		
		
		
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		String selected = (String) viewList.getSelectionModel().getSelectedItem();
		selected = selected.split(":")[0];
		int id= Integer.parseInt(selected);
		System.out.println(id);
		
		Login user= new Login();
		
		LoginDao.deleteLogin(id);
		
		viewList.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		
		users= user.getALL();
	
		
		for(int i=0;i<users.size();i++)
		{
			
			viewList.getItems().addAll(users.get(i).getUser_ID()+": "+users.get(i).getUsername());
		
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
			

			users= user.getUsersByName(searchUser);
			
			
			for(int i=0;i<users.size();i++)
			{
				
				viewList.getItems().addAll(users.get(i).getUser_ID()+": "+users.get(i).getUsername());
			
			
		}
		}
		
		
		
		
	}
	
	
	
	
	
}
