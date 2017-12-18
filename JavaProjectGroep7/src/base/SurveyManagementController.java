package base;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.TableView;
import javafx.util.Callback;


public class SurveyManagementController {
	@FXML
	private TableView<Survey> questionTable;
	@FXML
	private TableColumn<Survey,Integer> SurveyID;
	@FXML
	private TableColumn<Survey,Integer> QuestionID;
	@FXML
	private TableColumn<Survey,String> Questions;
	public GridPane color;
	
	
	/**
	 * 
	 */
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
	@FXML
	public void initialize()
	{
		SurveyID.setCellValueFactory(new Callback<CellDataFeatures<Survey, Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Survey, Integer> data) {
				return new SimpleIntegerProperty(data.getValue().getCompoundSurveyKey().getSurvey_ID()).asObject();
			}
		});
		QuestionID.setCellValueFactory(new Callback<CellDataFeatures<Survey, Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Survey, Integer> data) {
				return new SimpleIntegerProperty(data.getValue().getCompoundSurveyKey().getQuestion_ID()).asObject();
			}
		});
		Questions.setCellValueFactory(new Callback<CellDataFeatures<Survey, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Survey, String> data) {
				return new SimpleStringProperty(data.getValue().getQuestion());
			}
		});
		questionTable.setPlaceholder(new Label("No entry found."));
		ObservableList<Survey> list = FXCollections.observableArrayList(Survey.getAllSurveys());
		questionTable.setItems(list);
		String kleure= OptionsController.getColor();
		
		color.setStyle("-fx-background-color: #" + kleure);

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
	public void updateQuestion(ActionEvent event) throws Exception
	{
		Stage popup = new Stage();
		FXMLLoader f = new FXMLLoader(getClass().getResource("../gui/updateSurvey.fxml"));
		Parent root = (Parent) f.load();
		Scene scene = new Scene(root);
		popup.setTitle("UPDATE QUESTION");
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setResizable(false);
		popup.centerOnScreen();
		popup.setScene(scene);
		popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				initialize();
			}
		});
		UpdateSurveyController c = (UpdateSurveyController) f.getController();
		try {
			c.setQuestion(questionTable.getSelectionModel().getSelectedItem());
			popup.show();
			
		}catch(NullPointerException e)
		{
			ErrorMsg("Error", "you must select an item before updating 	");
		}
	}
	public void addQuestion(ActionEvent event) throws Exception
	{
		Stage popup = new Stage();
		FXMLLoader f = new FXMLLoader(getClass().getResource("../gui/addQuestion.fxml"));
		Parent root = (Parent) f.load();
		Scene scene = new Scene(root);
		popup.setTitle("ADD QUESTION");
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setResizable(false);
		popup.centerOnScreen();
		popup.setScene(scene);
		popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				initialize();
			}
		});
		addQuestionController c = (addQuestionController) f.getController();
		c.setNieuweQuestion(questionTable.getSelectionModel().getSelectedItem());
		popup.show();
		popup.show();
	}
	public void deleteQuestion(ActionEvent event) throws Exception
	{
		
		try {
			
			Survey c= new Survey();
			c=questionTable.getSelectionModel().getSelectedItem();
			Survey.deleteQuestion(c);
		
			questionTable.setPlaceholder(new Label("No entry found."));
			ObservableList<Survey> list = FXCollections.observableArrayList(Survey.getAllSurveys());
			
			questionTable.setItems(list);
		}catch(IllegalArgumentException e)
		{
			ErrorMsg("Error", "you must select something before deleting");
		}
	}
	
	public void addSurvey(ActionEvent event) throws Exception
	{
		Stage popup = new Stage();
		FXMLLoader f = new FXMLLoader(getClass().getResource("../gui/addSurvey.fxml"));
		Parent root = (Parent) f.load();
		Scene scene = new Scene(root);
		popup.setTitle("ADD SURVEY");
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setResizable(false);
		popup.centerOnScreen();
		popup.setScene(scene);
		popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				initialize();
			}
		});
		popup.show();
	}
	public static void ErrorMsg(String title, String msg)
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
