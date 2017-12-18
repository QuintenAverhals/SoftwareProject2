package base;

import dao.LocationDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;



public class LocationPopUpAddTrainingController {

	public AddTrainingController controller;
	public ComboBox<Location> existingDrop;
	public TextField streetName;
	public TextField streetNr;
	public TextField bus;
	public TextField city;
	public TextField zip;
	public TextField country;
	public Button submit;
	public AnchorPane color;

	@FXML
	private void clearBestaandeAdres() {
		existingDrop.getSelectionModel().clearSelection();
	}

	@FXML
	private void clearNieuweAdres() {
		streetName.setText("");
		streetNr.setText("");
		bus.setText("");
		city.setText("");
		zip.setText("");
		country.setText("");
	}

	@FXML
	private void handleSubmit() throws Exception {
		if (existingDrop.getValue() == null && streetName.getText().equals("")) {
			veiligeInvoer("Error", "You must create a new Location or select an existing one");
		} else {

			if (streetName.getText().equals("")) {

				controller.locationID.setText(new Integer(existingDrop.getValue().getLocation_ID()).toString());
			} else {
				Location l = new Location();
				if (!streetNr.getText().matches("[0-9]+") || !zip.getText().matches("[0-9]+")) {
					veiligeInvoer("Error", "StreetNr and/or zip must be a valid number");
				} else {

					l.setStreetname(streetName.getText());

					l.setStreetnumber(Integer.parseInt(streetNr.getText()));
					l.setBus(bus.getText());
					l.setCity(city.getText());
					l.setZip_code(zip.getText());
					l.setCountry(country.getText());

					Location.createNewLocation(l);

					controller.locationID.setText(new Integer(Location.getID(l)).toString());
					
					
					submit.setDisable(true);
					submit.setText("Location Has been added");

				}
			}
		}
	}

	public void initialize() {

		existingDrop.setItems(FXCollections.observableArrayList(LocationDAO.getAll()));

		Callback<ListView<Location>, ListCell<Location>> call = lv -> new ListCell<Location>() {

			@Override
			protected void updateItem(Location o, boolean empty) {
				super.updateItem(o, empty);
				setText(o == null ? "" : o.toString());
			}

		};
		existingDrop.setCellFactory(call);
		existingDrop.setButtonCell(call.call(null));
		String kleure= OptionsController.getColor();
		
		color.setStyle("-fx-background-color: #" + kleure);


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
