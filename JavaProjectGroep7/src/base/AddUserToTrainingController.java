package base;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddUserToTrainingController {
	public ListView viewList;
	public ListView viewListUsersInTraining;
	public ListView viewListUsersNotInTraining;
	public TextField TrainingenFilter;
	public TextField filterInTraining;
	public GridPane color;

	public void initialize() {
		viewListUsersNotInTraining.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Training training = new Training();
		List<Training> trainings;

		trainings = training.getAll();

		for (int i = 0; i < trainings.size(); i++) {

			viewList.getItems().addAll(trainings.get(i).getTraining_ID() + ": " + trainings.get(i).getTrainingNaam());

		}
		String kleure= OptionsController.getColor();

		color.setStyle("-fx-background-color: #" + kleure);

	}

	public void fillBlanksUsersInTraining(MouseEvent arg0) throws Exception {
		viewListUsersInTraining.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		String selected = (String) viewList.getSelectionModel().getSelectedItem();
		selected = selected.split(":")[0];
		int id = Integer.parseInt(selected);

		viewListUsersInTraining.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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

				viewListUsersInTraining.getItems().addAll(t.getCompound().getLoginID()+": "+naam);
			}
		}
		List<LoginWebsite> notIn= LoginWebsite.getAllNotInTraining(id);
		viewListUsersNotInTraining.getItems().clear();

		for (LoginWebsite l : notIn) {


				viewListUsersNotInTraining.getItems().addAll(l.getLoginID() + ": " + l.getUserName());
			}
		}


	public void filterTraining(ActionEvent event) throws Exception {

		viewList.getItems().clear();
		Training training = new Training();
		String searchTraining = TrainingenFilter.getText();
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
	public void logoutBtn(ActionEvent event) throws Exception {

		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/loginMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);

		window.show();

	}
	public void addToTraining(ActionEvent event) throws Exception {

		//to get LoginID (compound)
				String selected = (String) viewListUsersNotInTraining.getSelectionModel().getSelectedItem();
				selected = selected.split(":")[0];
				int id = Integer.parseInt(selected);



				//to get TrainingID (compound)


				String selectedTraining = (String) viewList.getSelectionModel().getSelectedItem();
				selectedTraining = selectedTraining.split(":")[0];
				int idselectedTraining = Integer.parseInt(selectedTraining);

				CompoundKeyTrainingWerknermer c= new CompoundKeyTrainingWerknermer();
				c.setLoginID(id);
				c.setTrainingID(idselectedTraining);



				TrainingWerknemer.addToTraining(c);



				viewListUsersInTraining.getItems().clear();
				viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

				String selected1 = (String) viewList.getSelectionModel().getSelectedItem();
				selected1 = selected1.split(":")[0];
				int id1 = Integer.parseInt(selected1);

				viewListUsersInTraining.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				Training training = new Training();
				List<Training> trainings;

				training = training.getByID(id1);

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

						viewListUsersInTraining.getItems().addAll(t.getCompound().getLoginID()+": "+naam);
					}
				}
				List<LoginWebsite> notIn= LoginWebsite.getAllNotInTraining(id1);
				viewListUsersNotInTraining.getItems().clear();

				for (LoginWebsite l : notIn) {


						viewListUsersNotInTraining.getItems().addAll(l.getLoginID() + ": " + l.getUserName());
					}
	}
	public void removeFromTraining(ActionEvent event) throws Exception {
		//to get LoginID (compound)
		String selected = (String) viewListUsersInTraining.getSelectionModel().getSelectedItem();
		selected = selected.split(":")[0];
		int id = Integer.parseInt(selected);



		//to get TrainingID (compound)


		String selectedTraining = (String) viewList.getSelectionModel().getSelectedItem();
		selectedTraining = selectedTraining.split(":")[0];
		int idselectedTraining = Integer.parseInt(selectedTraining);

		CompoundKeyTrainingWerknermer c= new CompoundKeyTrainingWerknermer();
		c.setLoginID(id);
		c.setTrainingID(idselectedTraining);



		TrainingWerknemer.DeleteFromTraining(c);





		viewListUsersInTraining.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		String selected1 = (String) viewList.getSelectionModel().getSelectedItem();
		selected1 = selected1.split(":")[0];
		int id1 = Integer.parseInt(selected1);

		viewListUsersInTraining.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Training training = new Training();
		List<Training> trainings;

		training = training.getByID(id1);

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

				viewListUsersInTraining.getItems().addAll(t.getCompound().getLoginID()+": "+naam);
			}
		}
		List<LoginWebsite> notIn= LoginWebsite.getAllNotInTraining(id1);
		viewListUsersNotInTraining.getItems().clear();

		for (LoginWebsite l : notIn) {


				viewListUsersNotInTraining.getItems().addAll(l.getLoginID() + ": " + l.getUserName());
			}
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
}
