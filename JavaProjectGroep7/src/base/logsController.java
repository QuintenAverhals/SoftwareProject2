package base;

import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;
import dao.LogfileDAO;

public class logsController {

	@FXML
	private TableView<Logfile> logTable;
	@FXML
	private TableColumn<Logfile,Integer> LogID;
	@FXML
	private TableColumn<Logfile,Integer> UserId;
	@FXML
	private TableColumn<Logfile,String> action;
	@FXML
	private TableColumn<Logfile,String> date;
	public GridPane color;


	/**
	 * @throws Exception
	 *
	 */
	@FXML
	public void initialize() throws Exception
	{
		LogID.setCellValueFactory(new Callback<CellDataFeatures<Logfile, Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Logfile, Integer> data) {
				return new SimpleIntegerProperty(data.getValue().getLogId()).asObject();
			}
		});
		UserId.setCellValueFactory(new Callback<CellDataFeatures<Logfile, Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Logfile, Integer> data) {
				return new SimpleIntegerProperty(data.getValue().getUserid()).asObject();
			}
		});
		action.setCellValueFactory(new Callback<CellDataFeatures<Logfile, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Logfile, String> data) {
				return new SimpleStringProperty(data.getValue().getAction());
			}
		});
		date.setCellValueFactory(new Callback<CellDataFeatures<Logfile, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Logfile, String> data) {
				return new SimpleStringProperty(data.getValue().getDate());
			}
		});

		logTable.setPlaceholder(new Label("No entry found."));
		ObservableList<Logfile> list = FXCollections.observableArrayList(Logfile.getALL());
		logTable.setItems(list);
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
