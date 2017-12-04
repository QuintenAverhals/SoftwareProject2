<<<<<<< HEAD
package base;


import java.util.Date;
import java.util.List;

public class Main {

	
	public static void main(String[] args) {
	
		/*OK:
		 * Location loc=new Location();
		loc.updateBus(13,"114A");
		
		Location loc=new Location();
		loc.updateCity(12, "Brussel");

		Location loc=new Location();
		loc.updateCountry(9, "halo");
		
		Location loc=new Location();
		loc.updateStreetname(9, "test");

		Location loc=new Location();
		loc.updateStreetnumber(10, 69);
		
		Location loc=new Location();
		loc.updateZipcode(10, "1711");*/
		
		/*OK:
		Location location= new Location();
		List <Location> locations =  location.getByID(11);
		
		for(int i=0;i<locations.size();i++ )
		{
			System.out.println(locations.get(i).toString());
			
			
		}
		*/
		
		
		
		
		
		
		
		
		
		
 /*OK:
  * Training t= new Training();
	List <Training> trainings = t.getAll();
	
	for(int i=0;i<trainings.size();i++ )
	{
		System.out.println(trainings.get(i).toString());
		
	}
	*/
	
		/*ok:
	 Training t= new Training();
		List <Training> trainings = t.getByID(7);
		
		for(int i=0;i<trainings.size();i++ )
		{
			System.out.println(trainings.get(i).toString());
			
		}*/
		
	
		/*ok:
		Training t=new Training();
		Status s=t.updateStatus(8, Status.COMPLETE);
	*/
		
	/*
		Training training=new Training();
		Date startTime=training.time(18, 43, 01);
		training.updateStartTime(8,startTime );
		*/
		
		//ok:
		/*Training training=new Training();
		training.updateVisibility(7, false);*/
		
		
		
	/*OK
		Training training=new Training();
		Date endTime= training.time(19, 11, 13);
		training.updateEndTime(8, endTime);
	*/
		/*
		Training training=new Training();
		Date end=training.Date(2017, 12, 30);
		training.updateEndDate(8, end);
		*/
	/*ok:
	Training training=new Training();
Date start=training.Date(2017, 04, 13);
training.updateStartDate(7, start);
		
	*/
		
		
	}
}
=======
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
		primaryStage.setTitle("Main Menu");
		
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("../GUI/Login.fxml"));
			loader.setController(new buttonsController());
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
>>>>>>> origin/dev_Tom
