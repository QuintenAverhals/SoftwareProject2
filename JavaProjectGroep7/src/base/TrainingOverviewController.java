package base;

import base.Training;
import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalQuery;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import dao.LocationDAO;
import dao.LoginDAO;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import dao.TrainingDAO;

public class TrainingOverviewController {

	//***********auteur:Chaimae**********
	public ListView viewList;
	public TextField trainingID;
	public TextField trainingName;

	public TextField filterView;

	public TextField bHour;
	public TextField bMinute;
	public TextField eHour;
	public TextField eMinute;
	public CheckBox cancel;
	public DatePicker startDate;
	public DatePicker endDate;
	public Button locationID;
	public ComboBox<Integer> surveyID;
	public GridPane color;
	
	public static int TRAINING_LOCATION_ID;
	public static int TRAINING_ID;

	@FXML
	public void initialize() {
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Training training = new Training();
		List<Training> trainings;

		trainings = training.getAll();

		for (int i = 0; i < trainings.size(); i++) {

			viewList.getItems().addAll(trainings.get(i).getTraining_ID() + ": " + trainings.get(i).getTrainingNaam());


		}


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

	public void updateTraining(ActionEvent event) throws Exception {

		String trainingLocationID = locationID.getText();
		int trainingp = 0;
		try {
			trainingp = Integer.parseInt(trainingLocationID);

		} catch (NumberFormatException e) {
			veiligeInvoer("ERROR", "You must select an item before Updating!");
		}
		if (trainingp != 0) {

			String trainingnaam = trainingName.getText();



			Training training = new Training();
			java.util.Date sd1;
			sd1 = Date.valueOf(startDate.getValue());
			/*
			 * if(startDate.getValue()==null) {
			 * 
			 * sd1= training.getStart_date();
			 * if(isSelected ){
			  cancel.setSelected(true);
			} else {
			   cancel.setSelected(false);
			}

			 * }else { sy=(sYear.getValue()); sm=(sMonth.getValue()); sd=(sDay.getValue());
			 * sd1=training.setDate(sy.getYear(),sm.getMonthValue(),sd.getDayOfMonth()); }
			 */

			java.util.Date ed1;

			ed1 = Date.valueOf(endDate.getValue());


			boolean s;
			if(cancel.isSelected()){
				s=true;
			} else {
				s=false;
			}
			System.out.println(s);
			/*
			 * if(endDate.getValue()==null) { ed1= test.getEnd_date(); }else {
			 * ey=(eYear.getValue()); em=(eMonth.getValue()); ed=(eDay.getValue()); ed1
			 * =eindDate.setDate(ey.getYear(), em.getMonthValue(), ed.getDayOfYear()); }
			 * 
			 * 
			 * 
			 * if(sd1.equals(null)|| ed1.equals(null)) {
			 * 
			 * training= training.startDatumByName(trainingnaam); sd1=
			 * training.getStart_date(); ed1= training.getEnd_date(); }
			 * 
			 */
			Training endTime = new Training();
			int h = Integer.parseInt(eHour.getText());
			int m = Integer.parseInt(eMinute.getText());
			java.util.Date et = endTime.setTime(h, m, 0);
			Training startTime = new Training();
			int hour = Integer.parseInt(bHour.getText());
			int minute = Integer.parseInt(bMinute.getText());
			java.util.Date st = startTime.setTime(hour, minute, 0);


			Training training2 = new Training();

			int idp = Integer.parseInt(trainingID.getText());

			java.util.Date today = new java.util.Date();
			if (sd1.before(today)) {
				veiligeInvoer("ERROR", "Start date must be in the future");
			} else {
				if (ed1.before(today)) {
					veiligeInvoer("ERROR", "end date must be in the future");
				} else {
					if (hour > 23 || hour < 0) {
						veiligeInvoer("ERROR", "You must enter a valid Start Time");
					} else {
						if (minute > 59 || minute < 0) {
							veiligeInvoer("ERROR", "You must enter a valid Start Time");
						} else {
							if (h > 23 || h < 0) {
								veiligeInvoer("ERROR", "You must enter a valid End Time");

							} else {
								if (m > 59 || m < 0) {
									veiligeInvoer("ERROR", "You must enter a valid End Time");

								} else {
									if (et.before(st)) {
										veiligeInvoer("ERROR", "End time cannot be before start Time");

									} else {
										if (ed1.before(sd1)) {
											veiligeInvoer("ERROR", "End Date cannot be before start date");

										} else {

											training2.updateALLTraining(idp, sd1, ed1, st, et,surveyID.getValue(),Integer.parseInt(locationID.getText()),
													true	,trainingnaam, s
													);
										}

									}
								}
							}
						}
					}
				}
			}

			viewList.getItems().clear();
			viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			List<Training> trainings = training2.getAll();

			for (int i = 0; i < trainings.size(); i++) {

				viewList.getItems()
				.addAll(trainings.get(i).getTraining_ID() + ": " + trainings.get(i).getTrainingNaam());

			}

		} else {

		}

	}

	public void deleleteBtn(ActionEvent event) throws Exception {

		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		String selected = (String) viewList.getSelectionModel().getSelectedItem();
		int id = 0;

		try {
			selected = selected.split(":")[0];
			id = Integer.parseInt(selected);
			System.out.println(id);

		} catch (NullPointerException e) {
			veiligeInvoer("Error", "You must select an item before Deleting");
		}
		if (id != 0) {

			Training training = new Training();

			training.updateVisibility(id);

			viewList.getItems().clear();
			viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			List<Training> trainings = training.getAll();

			for (int i = 0; i < trainings.size(); i++) {

				viewList.getItems()
				.addAll(trainings.get(i).getTraining_ID() + ": " + trainings.get(i).getTrainingNaam());

			}

		}
	}

	public void logoutBtn(ActionEvent event) throws Exception {

		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/LoginMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);

		window.show();

	}

	/*
	 * public void locationOnMap(ActionEvent event) throws Exception { LoginController
	 * current= new LoginController(); Login currentUser= current.getCurrentUser();
	 * Parent passwordForgottenParent =
	 * FXMLLoader.load(getClass().getResource("../gui/.fxml")); Scene
	 * passwordForgottenScene = new Scene(passwordForgottenParent);
	 * 
	 * Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
	 * window.setScene(passwordForgottenScene);
	 * 
	 * window.show(); }
	 * 
	 */
	public void goBack(ActionEvent event) throws Exception {
		LoginController current = new LoginController();
		Login currentUser = current.getCurrentUser();
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/TrainingMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);

		window.show();
	}

	public void fillBlanks(MouseEvent arg0) throws Exception {

		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		String selected = (String) viewList.getSelectionModel().getSelectedItem();
		selected = selected.split(":")[0];
		int id = Integer.parseInt(selected);
		System.out.println(id);

		cancel.setSelected(false);

		Training training = new Training();
		training = training.getByID(id);
		boolean slc= training.getcancel();

		if(slc==true ){
			cancel.setSelected(true);
		} else {
			cancel.setSelected(false);
		}
		System.out.println(training.toString());

		trainingID.setText(selected);
		trainingName.setText(training.getTrainingNaam());

		for (Integer i : surveyID.getItems()) {
			if (i.equals(training.getSurveyID())) {
				surveyID.getSelectionModel().select(i);
				break;
			}
		}

		String loc = "" + training.getLocationID();
		locationID.setText(loc);

		// ***********************

		Training train = new Training();

		endDate.setValue(new Date(training.getEnd_date().getTime()).toLocalDate());

		startDate.setValue(new Date(training.getStart_date().getTime()).toLocalDate());

		// ***************
		java.util.Date timeb = training.getStart_time();
		String begin = "" + timeb.getHours();
		bHour.setText(begin);

		java.util.Date timebe = training.getStart_time();
		String beginM = "" + timebe.getMinutes();
		bMinute.setText(beginM);

		// *****************
		java.util.Date endM1 = training.getEnd_time();
		String eindM = "" + endM1.getMinutes();
		eMinute.setText(eindM);

		java.util.Date endU = training.getEnd_time();
		String eindUren = "" + endU.getHours();
		eHour.setText(eindUren);


	}

	public void newLocation(ActionEvent event) throws IOException {

		Stage popup = new Stage();
		FXMLLoader f = new FXMLLoader(getClass().getResource("../gui/locationPopUp.fxml"));
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
		locationPopUpController c = (locationPopUpController) f.getController();
		c.controller = this;
		popup.show();
	}

	public void Load_Content(ActionEvent event) throws Exception {

		viewList.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Training training = new Training();

		List<Training> trainings = training.getAll();

		for (int i = 0; i < trainings.size(); i++) {

			viewList.getItems().addAll(trainings.get(i).getTraining_ID() + ": " + trainings.get(i).getTrainingNaam());

		}

	}

	public void filterView(ActionEvent event) throws Exception {

		viewList.getItems().clear();
		Training training = new Training();
		String searchTraining = filterView.getText();
		List<Training> trainings = training.getAll();

		if (searchTraining.equals("")) {

			viewList.getItems().clear();
			viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);



			for (int i = 0; i < trainings.size(); i++) {

				viewList.getItems().addAll(trainings.get(i).getTraining_ID() + ": " + trainings.get(i).getTrainingNaam());

			}

		} else {



			for (int i = 0; i < trainings.size(); i++) {
				if(trainings.get(i).getTrainingNaam().contains(searchTraining))
				{
					viewList.getItems().addAll(trainings.get(i).getTraining_ID() + ": " + trainings.get(i).getTrainingNaam());
				}


			}
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

	public void locationOnMap(ActionEvent event) throws IOException {
		if (!locationID.getText().equals("Choose Location")) {
			TRAINING_LOCATION_ID = Integer.parseInt(locationID.getText());

			Parent locationOnMapParent = FXMLLoader.load(getClass().getResource("../gui/LocationOnMap.fxml"));
			Scene locationOnMapScene = new Scene(locationOnMapParent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(locationOnMapScene);

			window.show();
		}
	}

	public void addBooks(ActionEvent event) throws IOException {
		if (!trainingID.getText().equals("")) {
			TRAINING_ID = Integer.parseInt(trainingID.getText());

			Parent addBooksParent = FXMLLoader.load(getClass().getResource("../gui/addBook.fxml"));
			Scene addBooksScene = new Scene(addBooksParent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(addBooksScene);

			window.show();
		}
	}
}