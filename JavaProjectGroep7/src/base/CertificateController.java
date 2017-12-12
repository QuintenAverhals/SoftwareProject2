package base;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CertificateController {

	public ListView viewList;
	public TextField filterView;
	public ListView viewListInTraining;

	public void initialize() {
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Training training = new Training();
		List<Training> trainings;

		trainings = training.getAllPastTrainings();

		for (int i = 0; i < trainings.size(); i++) {

			viewList.getItems().addAll(trainings.get(i).getTraining_ID() + ": " + trainings.get(i).getTrainingNaam());

		}

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

	public void DownloadCertificate(ActionEvent event) throws Exception {

		String selected = (String) viewList.getSelectionModel().getSelectedItem();
		int TrainingID = 0;
		try {

			selected = selected.split(":")[0];
			TrainingID = Integer.parseInt(selected);

		} catch (NullPointerException e) {
			passwordNotSame("Error", "Cannot download without selecting an item!");
		}

		if (TrainingID != 0) {

			String selectedWerknemerName = (String) viewListInTraining.getSelectionModel().getSelectedItem();
			selectedWerknemerName = selectedWerknemerName.substring(selectedWerknemerName.lastIndexOf(":") + 2);

			try {

				Certificate c2 = Certificate.getCertificateByTrainingIDEmployeeName(TrainingID, selectedWerknemerName);
				String fileName = c2.getDocName();
				Boolean download = c2.downloadFromServer(fileName);
				passwordNotSame("","You're file has successfully been downloaded");
			} catch (IndexOutOfBoundsException e) {
				passwordNotSame("Error", "this user has no certificate for this Training");
			}

		}
	}

	public void FillBlanks(MouseEvent arg0) throws Exception {
		viewListInTraining.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		String selected = (String) viewList.getSelectionModel().getSelectedItem();
		selected = selected.split(":")[0];
		int id = Integer.parseInt(selected);

		viewListInTraining.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Training training = new Training();
		List<Training> trainings;

		training = training.getByID(id);

		List<TrainingWerknemer> usersinTraining = TrainingWerknemer.getALL();
		List<LoginWebsite> logins = LoginWebsite.getAll();

		for (TrainingWerknemer t : usersinTraining) {
			if (training.getTraining_ID() == t.getCompound().getTrainingID()) {
				String naam = "";
				for (LoginWebsite l : logins) {
					if (l.getLoginID() == t.getCompound().getLoginID()) {
						naam = l.getUserName();
					}
				}

				viewListInTraining.getItems().addAll(t.getCompound().getLoginID() + ": " + naam);
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
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/TrainingMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);

		window.show();
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

				viewList.getItems()
						.addAll(trainings.get(i).getTraining_ID() + ": " + trainings.get(i).getTrainingNaam());

			}

		} else {

			for (int i = 0; i < trainings.size(); i++) {
				if (trainings.get(i).getTrainingNaam().contains(searchTraining)) {
					viewList.getItems()
							.addAll(trainings.get(i).getTraining_ID() + ": " + trainings.get(i).getTrainingNaam());
				}

			}
		}

	}

	public static void passwordNotSame(String title, String msg) {
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
