package base;

import java.util.Date;
import java.util.List;

import dao.LoginDAO;
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


public class TrainingOverviewController {
	public ListView viewList;
	public TextField TrainingID;
	public TextField trainingName;
	public TextField startDay;
	public TextField startmonth;
	public TextField startYear;
	public TextField endDay;
	public TextField endMonth;
	public TextField EndYear;
	
	public TextField locationID;
	public TextField beginUur;
	public TextField beginMinuut;
	public TextField eindUur;
	public TextField eindMinuut;
	public TextField filterView;
	
	

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
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/trainingMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	public void locationOnMap(ActionEvent event) throws Exception
	{
		LoginController current= new LoginController();
		Login currentUser= current.getCurrentUser();
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/locationOnMap.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	
	
		
	
	
	
}

