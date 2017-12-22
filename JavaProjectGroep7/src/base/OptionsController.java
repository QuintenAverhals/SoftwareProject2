package base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.LoginDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OptionsController {

	public TextField maxTraining;
	public GridPane color;
	public ColorPicker colorPicker;
	public static Color kleur;
	public GridPane colorr;

	private static List<String> item;

	public void initialize() {

		String kleure= OptionsController.getColor();

		colorr.setStyle("-fx-background-color: #" + kleure);

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

	public void chooseImage(ActionEvent event) throws Exception
	{

		File logo= Certificate.chooseFile();
		String fileName = "logo.png";
		try {
			Certificate.uploadToServer(logo);

			Boolean download = Certificate.downloadFromServerLogo("logo.png");
			veiligeInvoer("","You're file has successfully been downloaded");
			Logfile.addLogs(LoginController.currentUser.getUser_ID(), "Logo has been changed by user:" +LoginController.currentUser.getUsername());
		}catch(NullPointerException e)
		{

		}



	}

	public static String getColor() {
		java.awt.Color color = null;
		Session session = Main.factory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Options");
		Options op = (Options) query.uniqueResult();

		session.getTransaction().commit();





		return op.getBackground_Color();
	}

	public void submit(ActionEvent event) throws Exception {
		colorr.setBackground(new Background(new BackgroundFill(colorPicker.getValue(), CornerRadii.EMPTY, Insets.EMPTY)));

		if (maxTraining.getText().equals("")) {
			Session session = Main.factory.getCurrentSession();
			session.beginTransaction();
			String hex = Integer.toHexString(colorPicker.getValue().hashCode()).substring(0, 6).toUpperCase();
			Query query = session.createQuery("UPDATE Options SET Background_Color= '" + hex + "'");

			query.executeUpdate();
			session.getTransaction().commit();

		} else {
			if (!maxTraining.getText().matches("[0-9]+")) {
				veiligeInvoer("Error", "Max trainings must be a number!");

			} else {

				Session session = Main.factory.getCurrentSession();
				session.beginTransaction();

				String hex = Integer.toHexString(colorPicker.getValue().hashCode()).substring(0, 6).toUpperCase();

				Query query = session.createQuery("UPDATE Options SET Background_Color= '" + hex + "'");
				// Query query2= session.createQuery("UPDATE OPTIONS SET Logo_filename='"++"'");
				Query query3 = session.createQuery("UPDATE Options SET maxTrainingen=" + maxTraining.getText());

				query.executeUpdate();
				query3.executeUpdate();
				session.getTransaction().commit();
				/*
				 * Logfile log= new Logfile(); LoginController currentUserr= new
				 * LoginController(); int current= currentUserr.getCurrentUser().getUser_ID();
				 * log.addLogs(current, "User: "+currentUserr.getCurrentUser().getUsername()
				 * +" updated end time for training "+id);
				 */

			}

			kleur = colorPicker.getValue();
		}
		Logfile.addLogs(LoginController.currentUser.getUser_ID(), "Options has been changed by user: " +LoginController.currentUser.getUsername());


	}

	public static Color getKleur() {
		return kleur;
	}

	public static void setKleur(Color kleur) {
		OptionsController.kleur = kleur;
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
