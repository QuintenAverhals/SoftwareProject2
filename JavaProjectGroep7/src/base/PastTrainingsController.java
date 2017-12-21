package base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
public class PastTrainingsController{
	
	//auteur: chaimae
	
	public TextField filterTrainingsSearchBar;
	public ListView viewList;
	public ListView viewListUsersInTraining;
	public DatePicker endDate;
	public DatePicker startDate;
	public Label trainingID;
	public Label trainingName;
    public Label locationID;
    public CheckBox cancel;
    public Label SurveyID;
    public GridPane color;
  

 
   
  public void mainMenu(ActionEvent event) throws Exception
	{
		LoginController current= new LoginController();
		Login currentUser= current.getCurrentUser();
		
		
		if(currentUser.isAdmin()==true)
		{
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenu.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);
			
			window.show();
		}else {
			Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/mainMenuNormaleUser.fxml"));
			Scene passwordForgottenScene = new Scene(passwordForgottenParent);
			
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(passwordForgottenScene);
			
			window.show();
			
		}
	}
	public void initialize()
	{
		
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Training training= new Training();
		List<Training> trainings;
		
		trainings= training.getAllPastTrainings();
	
		
		for(int i=0;i<trainings.size();i++)
		{
			
			viewList.getItems().addAll(trainings.get(i).getTraining_ID()+": "+trainings.get(i).getTrainingNaam());
			
		}
		String kleure= OptionsController.getColor();
		
		color.setStyle("-fx-background-color: #" + kleure);

		
		}
	public void upLoadCertificate(ActionEvent event) throws Exception
	{
		String selected = (String) viewList.getSelectionModel().getSelectedItem();
		int TrainingID=0;
		
		try {
			selected = selected.split(":")[0];
			TrainingID = Integer.parseInt(selected);
			
		}catch(NullPointerException e)
		{
			passwordNotSame("Error", "Must select an item before uploading");
		}
		
		if(TrainingID!=0)
		{
			
		
		String selectedWerknemerName = (String) viewListUsersInTraining.getSelectionModel().getSelectedItem();
		selectedWerknemerName = selectedWerknemerName.substring(selectedWerknemerName.lastIndexOf(":")+2);
		String fileName = null;
		File c= Certificate.chooseFile();
		try {
			
			fileName = Certificate.getFileName(c);
		}catch(NullPointerException e) {
			
		}
		try {
			Certificate.uploadToServer(c);
			Certificate.addtoDatabase(TrainingID,LoginController.currentUser.getUser_ID(), selectedWerknemerName, fileName);
			passwordNotSame("", "Your file has successfully been uploaded");
			
		}catch(NullPointerException e)
		{
			passwordNotSame("", "This user already has a certificate");

		}
		
		
		
		}
		
	}
	public void fillBlanks(MouseEvent event) throws Exception 
	{
		
        viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        String selected = (String) viewList.getSelectionModel().getSelectedItem();
        selected = selected.split(":")[0];
        int id= Integer.parseInt(selected);
        System.out.println(id);
        
        Training training= new Training();
		training= training.getByID(id);
		
		System.out.println(training.toString());
		
		
		trainingID.setText(selected);
		trainingName.setText(training.getTrainingNaam());

		
        
     String loc= ""+training.getLocationID();	
     locationID.setText(loc);
     
     String surv=""+training.getSurveyID();
     SurveyID.setText(surv);
    //***********************
     final String pattern = "dd/MM/YYYY";
     StringConverter converter = new StringConverter<LocalDate>() {
         DateTimeFormatter dateFormatter = 
             DateTimeFormatter.ofPattern(pattern);
         @Override
         public String toString(LocalDate date) {
             if (date != null) {
                 return dateFormatter.format(date);
             } else {
                 return "";
             }
         }
         @Override
         public LocalDate fromString(String string) {
             if (string != null && !string.isEmpty()) {
                 return LocalDate.parse(string, dateFormatter);
             } else {
                 return null;
             }
         }
     };   
     Training train= new Training();
     
   
     Date end=training.getEnd_date();
     int endDay=end.getDay();
    int endM=end.getMonth();
    int endYear=end.getYear();
   LocalDate endDatum=LocalDate.of(endYear,endM,endDay);
/*
   endDate.setConverter(converter);
   endDate.setPromptText(pattern.toLowerCase());
   endDate.setValue(endDatum);
 */
   Date st=training.getStart_date();
   int startDay=st.getDay();
   int startMonth=st.getMonth();
   int startYear=st.getYear();
   
 LocalDate start=LocalDate.of(startYear, startMonth, startDay);
 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 
 String startstart= sdf.format(st);
 startDate.setPromptText(startstart);
   
 SimpleDateFormat sv = new SimpleDateFormat("dd/MM/yyyy");


 String endend=sv.format(end);
 endDate.setPromptText(endend);
 
   
   
 
		
  	
 
 	viewListUsersInTraining.getItems().clear();

 	viewListUsersInTraining.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	Training training1 = new Training();
	List<Training> trainings;

	training1 = training1.getByID(id);

	List<TrainingWerknemer> usersinTraining = TrainingWerknemer.getALL();
	List<LoginWebsite> logins = LoginWebsite.getAll();

	for (TrainingWerknemer t : usersinTraining) {
		if (training1.getTraining_ID() == t.getCompound().getTrainingID()) {
			String naam = "";
			for (LoginWebsite l : logins) {
				if (l.getLoginID() == t.getCompound().getLoginID()) {
					naam = l.getUserName();
				}
			}

			viewListUsersInTraining.getItems().addAll(t.getCompound().getLoginID()+": "+naam);
		}
	}

	}
	public void logoutBtn(ActionEvent event) throws Exception
	{
	
	

		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/LoginMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
		
	}
	
	public void goBack(ActionEvent event) throws Exception
	{
		LoginController current= new LoginController();
		Login currentUser= current.getCurrentUser();
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/TrainingMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	public void filterView(ActionEvent event) throws Exception {

		viewList.getItems().clear();
		Training training = new Training();
		String searchTraining = filterTrainingsSearchBar.getText();
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
	public static void passwordNotSame(String title, String msg)
	{
		Stage window= new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label= new Label();
		
		label.setText(msg);
		Button closeButton= new Button("Close the window");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout= new VBox(10);
		layout.getChildren().add(label);
		layout.getChildren().add(closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	}
}
