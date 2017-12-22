package base;

import dao.StatisticsDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StatisticsController {
	public GridPane gridPane;
	
	public TableView<Statistics> certificateTable;
	public TableColumn<Statistics, String> certificateNameCol;
	public TableColumn<Statistics, Integer> certificateAmountCol;
	
	public TableView<Statistics> trainingTable;
	public TableColumn<Statistics, String> trainingNameCol;
	public TableColumn<Statistics, Integer> trainingAmountCol;
	
	public void initialize() {
		String color = OptionsController.getColor();
		System.out.println(color);			
		gridPane.setStyle("-fx-background-color: #" + color);
		
		
		ObservableList<Statistics> certificateData = FXCollections.observableArrayList(StatisticsDAO.getTopCertificates());
		
		certificateNameCol.setCellValueFactory(
		    new PropertyValueFactory<Statistics, String>("Name")
		);
		certificateAmountCol.setCellValueFactory(
		    new PropertyValueFactory<Statistics, Integer>("Amount")
		);
		
		certificateTable.setItems(certificateData);
		
		ObservableList<Statistics> trainingData = FXCollections.observableArrayList(StatisticsDAO.getTopTrainings());
		
		trainingNameCol.setCellValueFactory(
		    new PropertyValueFactory<Statistics, String>("Name")
		);
		trainingAmountCol.setCellValueFactory(
		    new PropertyValueFactory<Statistics, Integer>("Amount")
		);
		
		trainingTable.setItems(trainingData);
	}
	
	public void logOut(ActionEvent event) throws Exception {
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/loginMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);

		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);

		window.show();
	}
	
	public void goBack(ActionEvent event) throws Exception {
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
	
	
}
