package base;

import java.io.IOException;

import base.buttonsController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {

    
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Login");
		
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("loginGui.fxml"));
			loader.setController(new Controller());
			AnchorPane page = (AnchorPane) loader.load();
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.show();

			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);	
		
		
	}
}
