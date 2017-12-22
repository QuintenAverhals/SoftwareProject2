package base;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CheckLocationOnMapController {
	public ImageView locationImgView;
	
	
	@FXML
	public void initialize() {
		
		List<Location> places = new Location().getByID(TrainingOverviewController.TRAINING_LOCATION_ID);
		Location place = places.get(0);
		
		String parsedAddress = place.getStreetname().replaceAll("\\s+", "+") + "+" + place.getStreetnumber() + "," + place.getZip_code() + "," + place.getCity() + "," + place.getCountry();
		
		int width =(int) locationImgView.getFitWidth();
		int height =(int) locationImgView.getFitHeight();
		
		Image map = new Image("https://maps.googleapis.com/maps/api/staticmap?zoom=14"
				+ "&center=" + parsedAddress
				+ "&size=" + width + "x" + height
				+ "&markers=color:red%7Clabel:A%7C" + parsedAddress
				+ "&key=" + Credentials.API_KEY_MAPS);
	
		locationImgView.setImage(map);
	}

	public void goBack(ActionEvent event) throws Exception
	{
		
		Parent parentResource = FXMLLoader.load(getClass().getResource("../gui/trainingOverview.fxml"));
		Scene parentScene = new Scene(parentResource);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(parentScene);
		
		window.show();
	}
	public void logoutBtn(ActionEvent event) throws Exception
	{
		Parent parentResource = FXMLLoader.load(getClass().getResource("../gui/loginMenu.fxml"));
		Scene parentScene = new Scene(parentResource);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(parentScene);
		
		window.show();
	}

	
}
