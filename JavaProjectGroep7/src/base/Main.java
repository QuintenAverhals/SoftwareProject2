package base;

import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dao.LoginDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	public static SessionFactory factory;
	private static Stage stage;

	@Override
	public void start(Stage window) throws Exception {

		stage = window;
		Parent root = FXMLLoader.load(getClass().getResource("../gui/LoginMenu.fxml"));

		window.setTitle("Software Project - Groep 7");
		window.setScene(new Scene(root, 700, 400));
		window.setResizable(false);

		window.show();

	}
	/*
	 * public static void setRoot(Parent root) { stage.getScene().setRoot(root); }
	 */

	public static void main(String[] args) {
		Boolean download = Certificate.downloadFromServerLogo("logo.png");
		try {
			factory = new Configuration().configure()
					.addAnnotatedClass(TrainingWerknemer.class)
					.addAnnotatedClass(Certificate.class)
					.addAnnotatedClass(Logfile.class)
					.addAnnotatedClass(Options.class)
					.addAnnotatedClass(Survey.class)
					.addAnnotatedClass(Login.class)
					.addAnnotatedClass(Location.class)
					.addAnnotatedClass(Training.class)
					.addAnnotatedClass(LoginWebsite.class)
					.addAnnotatedClass(TrainingBook.class)
					.buildSessionFactory();
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			System.out.println("Error -> Application will exit!");
			System.exit(0);

		}
		launch(args);
		factory. close();
	}

}
