package base;

import java.io.IOException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

//********auteur: Chaimae********************
public class AddTrainingController {

	public TextField TrainingID;
	public TextField TrainingName;

	public DatePicker startDate;
	public DatePicker endDate;

	public TextField LocationID;
	public TextField startHour;
	public TextField startMinute;
	public TextField endHour;
	public TextField endMinute;
	public CheckBox cancel;
	public Button locationID;
	public ComboBox<Integer> surveyID;
	public Button mainMenuBtn;
	public GridPane color;

	@FXML
	public void initialize() {

		surveyID.setItems(FXCollections.observableArrayList(Survey.getAllSurveysIDS()));

		Callback<ListView<Integer>, ListCell<Integer>> call = lv -> new ListCell<Integer>() {

			@Override
			protected void updateItem(Integer o, boolean empty) {
				super.updateItem(o, empty);
				setText(o == null ? "" : o.toString());
			}

		};
		surveyID.setCellFactory(call);
		surveyID.setButtonCell(call.call(null));
		String kleure= OptionsController.getColor();
		
		color.setStyle("-fx-background-color: #" + kleure);

	}

	public void mainMenu(ActionEvent event) throws Exception {
		LoginController current = new LoginController();
		Login currentUser = current.getCurrentUser();

		if (currentUser.isAdmin() == true) {
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenu.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);

			window.show();
		} else {
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenuNormaleUser.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);

			window.show();

		}
	}

	public void newLocation(ActionEvent event) throws IOException {

		Stage popup = new Stage();
		FXMLLoader f = new FXMLLoader(getClass().getResource("../gui/LocationPopUpAddTraining.fxml"));
		Parent root = (Parent) f.load();
		Scene scene = new Scene(root);
		popup.setTitle("Location");
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setResizable(false);
		popup.centerOnScreen();
		popup.setScene(scene);
		popup.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				initialize();
			}
		});
		LocationPopUpAddTrainingController c = (LocationPopUpAddTrainingController) f.getController();
		c.controller = this;
		popup.show();
	}

	public void submitBtn(ActionEvent event) throws Exception {

		if (TrainingName.getText().equals("") || startDate.getValue() == null || endDate.getValue() == null
				|| startHour.getText().equals("") || startMinute.getText().equals("") || endHour.getText().equals("")
				|| endMinute.getText().equals("") || locationID.getText().equals("") || surveyID.getValue() == null) {
			veiligeInvoer("Error", "All fields must be filled in");
		} else {
			if(!startHour.getText().matches("[0-9]+")||!startMinute.getText().matches("[0-9]+")||!endHour.getText().matches("[0-9]+")||!endMinute.getText().matches("[0-9]+"))
			{
				veiligeInvoer("Error", "Zbeubzbeub");

			}else {
				
			String trainingnaam = TrainingName.getText();

			Training training = new Training();

			LocalDate sy;
			LocalDate sm;
			LocalDate sd;

			java.util.Date sd1;
			
				
			sd1 = java.sql.Date.valueOf(startDate.getValue());
			LocalDate ey;
			LocalDate em;
			LocalDate ed;
			Training eindDate = new Training();
			java.util.Date ed1;
			ed1 = java.sql.Date.valueOf(endDate.getValue());

			Training startTime = new Training();
			int hour = Integer.parseInt(startHour.getText());
			int minute = Integer.parseInt(startMinute.getText());
			Date st = startTime.setTime(hour, minute, 0);

			Training endTime = new Training();
			int h = Integer.parseInt(endHour.getText());
			int m = Integer.parseInt(endMinute.getText());
			Date et = endTime.setTime(h, m, 0);
			
			
			String BeginUur = startHour.getText();
			int BeginUurp = Integer.parseInt(BeginUur);

			String BeginMinuut = startMinute.getText();
			int BeginMinuutp = Integer.parseInt(BeginMinuut);

			Training tr = new Training();
			Date startTime1 = tr.setTime(BeginUurp, BeginMinuutp, 0);

			String endUur = endHour.getText();
			int endUurp = Integer.parseInt(endUur);

			String endMinuut = endMinute.getText();
			int endMinutes = Integer.parseInt(endMinuut);

			Training tra = new Training();
			Date endTime1 = tra.setTime(endUurp, endMinutes, 0);

			Training training2 = new Training();
			Date today = new Date();

			if (sd1.before(today)) {
				veiligeInvoer("ERROR", "Start date must be in the future");
			} else {
				if (ed1.before(today)) {
					veiligeInvoer("ERROR", "end date must be in the future");
				} else {
					if (BeginUurp > 23 || BeginUurp < 0) {
						veiligeInvoer("ERROR", "You must enter a valid Start Time");
					} else {
						if (BeginMinuutp > 59 || BeginMinuutp < 0) {
							veiligeInvoer("ERROR", "You must enter a valid Start Time");
						} else {
							if (endUurp > 23 || endUurp < 0) {
								veiligeInvoer("ERROR", "You must enter a valid End Time");

							} else {
								if (endMinutes > 59 || endMinutes < 0) {
									veiligeInvoer("ERROR", "You must enter a valid End Time");

								} else {
									if (endTime1.before(startTime1)) {
										veiligeInvoer("ERROR", "End time cannot be before start Time");

									} else {
										if (ed1.before(sd1)) {
											veiligeInvoer("ERROR", "End Date cannot be before start date");

										} else {

											training2.createNewTraining(sd1, ed1, startTime1, endTime1,
													surveyID.getValue(), Integer.parseInt(locationID.getText()), true,
													trainingnaam);
										}

									}
								}
							}
						}
					}
				}
			}
		}
		}
	}

	public void logoutBtn(ActionEvent event) throws Exception {
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/loginMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);

		window.show();
	}

	public void goBack(ActionEvent event) throws Exception {

		LoginController current = new LoginController();
		Login currentUser = current.getCurrentUser();

		if (currentUser.isAdmin() == true) {
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/TrainingMenu.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);

			window.show();
		} else {
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/TrainingMenu.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);

			window.show();

		}
	}

	public static void veiligeInvoer(String title, String msg) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label();

		label.setText(msg);
		Button closeButton = new Button("Close the window");
		closeButton.setOnAction(e -> window.close());

		VBox layout = new VBox(10);
		layout.getChildren().add(label);
		layout.getChildren().add(closeButton);
		layout.setAlignment(Pos.CENTER);

		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();

	}

}
