package base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginGui extends Application {

	private static Stage stage;
	@Override
	public void start(Stage window) throws Exception {
		
		stage = window;
		Parent root = FXMLLoader.load(getClass().getResource("LoginGui.fxml"));
		
		
		
		window.setTitle("Login");
		window.setScene(new Scene(root,700,400));
		
		window.show();
		
	}
	/*
	public static void setRoot(Parent root)
	{
		stage.getScene().setRoot(root);
	}*/
	
	public static void main(String[] args) {
		launch(args);
	}

}


