package base;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class buttonsController implements Initializable {

	/*@FXML // fx:id="inlogButton"
    private Button inlogButton;
	
	
	void clickButton(ActionEvent event) {
		
		try {
			Parent root;
            root = FXMLLoader.load(Main.class.getResource("Login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            // Hide this current window (if this is what you want)
           //PrimaryStage.getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	*/
	
	@FXML
    Button inlogButton;
	Button bookMenuButton;
	

    private void handleInlogButtonAction(ActionEvent event) {
         // Button was clicked, do something...
    	try {
			Parent root;
            root = FXMLLoader.load(Main.class.getResource("MainMenu.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Main Menu");
            stage.setScene(new Scene(root, 600, 300));
            stage.show();
            // Hide this current window (if this is what you want)
           //PrimaryStage.getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
	}
    
    private void handleBookMenuButtonAction(ActionEvent event) {
        // Button was clicked, do something...
   	try {
			Parent root;
           root = FXMLLoader.load(Main.class.getResource("BoekMenu.fxml"));
           Stage stage = new Stage();
           stage.setTitle("Boek Menu");
           stage.setScene(new Scene(root, 600, 300));
           stage.show();
           // Hide this current window (if this is what you want)
          //PrimaryStage.getScene().getWindow().hide();
       }
       catch (IOException e) {
           e.printStackTrace();
       }
		
	}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        inlogButton.setOnAction(this::handleInlogButtonAction);
        bookMenuButton.setOnAction(this::handleBookMenuButtonAction);
    }
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

}
