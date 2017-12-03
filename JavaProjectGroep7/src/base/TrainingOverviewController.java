package base;

import java.util.Date;
import java.util.List;

import dao.LoginDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dao.TrainingDAO;

public class TrainingOverviewController {
	public ListView viewList;
	public TextField TrainingID;
	public TextField trainingName;
	public TextField startDay;
	public TextField startmonth;
	public TextField startYear;
	public TextField endDay;
	public TextField endMonth;
	public TextField EndYear;
	
	public TextField locationID;
	public TextField beginUur;
	public TextField beginMinuut;
	public TextField eindUur;
	public TextField eindMinuut;
	public TextField filterView;
	
	@FXML
	
	public void initialize()
	{
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Training training= new Training();
		List<Training> trainings;
		
		trainings= training.getAll();
	
		
		for(int i=0;i<trainings.size();i++)
		{
			
			viewList.getItems().addAll(trainings.get(i).getTraining_ID()+": "+trainings.get(i).getTrainingNaam());

		}
	}


	public void logoutBtn(ActionEvent event) throws Exception
	{
	
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/loginGui.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
		
	}
	
	public void goBack(ActionEvent event) throws Exception
	{
		Controller current= new Controller();
		Login currentUser= current.getCurrentUser();
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/TrainingMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	public void locationOnMap(ActionEvent event) throws Exception
	{
		Controller current= new Controller();
		Login currentUser= current.getCurrentUser();
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/locationOnMap.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);
		
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);
		
		window.show();
	}
	
	public void fillBlanks(ActionEvent event) throws Exception
	{
    
        viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        String selected = (String) viewList.getSelectionModel().getSelectedItem();
        selected = selected.split(":")[0];
        int id= Integer.parseInt(selected);
        System.out.println(id);
        
        Training training= new Training();
		training= training.getByID(id);
		
		System.out.println(training.toString());
		
		
		TrainingID.setText(selected);
		trainingName.setText(training.getTrainingNaam());
	
	
		Date date= training.getStart_date();
	
		String day=""+date.getDay();
		startDay.setText(day);

		Date end =training.getEnd_date();
		String endd=""+end.getDay();
		endDay.setText(endd);
		
	/*
	String survey= ""+training.getSurveyID();
	SurveyID.setText(survey);
	*/
	
	String loc= ""+training.getLocationID();	
    locationID.setText(loc);
    
    
    Date d=training.getStart_date();
    String month=""+date.getMonth();
    startmonth.setText(month);
    
    Date y=training.getStart_date();
    String year=""+date.getYear();
    startYear.setText(year);
	
    Date endM=training.getEnd_date();
    String endMO=""+endM.getMonth();
    endMonth.setText(endMO);
    
   Date endY=training.getEnd_date();
   String endYear=""+endY.getYear();
   EndYear.setText(endYear);
	
		
   Date timeb=training.getStart_time();
   String begin=""+timeb.getHours();
   beginUur.setText(begin);
   
   Date timebe=training.getStart_time();
   String beginM=""+timeb.getMinutes();
   beginMinuut.setText(beginM);
   
   Date endM1=training.getEnd_time();
   String eindM=""+timeb.getMinutes();
   eindMinuut.setText(eindM);

	   Date endU=training.getEnd_time();
	   String eindUren=""+endU.getHours();
	   eindUur.setText(eindUren);
		
        


	}
	public void Load_Content(ActionEvent event) throws Exception
	{

		viewList.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Training training= new Training();
		
		
		List <Training> trainings= training.getAll();
	
		
		for(int i=0;i<trainings.size();i++)
		{
			
			viewList.getItems().addAll(trainings.get(i).getTraining_ID()+": "+trainings.get(i).getTrainingNaam());
		
		}
		
	
	}
	public void filterView(ActionEvent event) throws Exception
	{
		
		viewList.getItems().clear();
		viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		Training training= new Training();
		String searchTraining= filterView.getText();
		
		if(searchTraining.equals(""))
		{
			
			viewList.getItems().clear();
			viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			
			
			
			
			List <Training> trainings= training.getAll();
		
			
			for(int i=0;i<trainings.size();i++)
			{
				
				viewList.getItems().addAll(trainings.get(i).getTraining_ID()+": "+trainings.get(i).getTrainingNaam());
			
			}
			
		}else {
			
			viewList.getItems().clear();
			viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			
			
			
			
			List <Training> trainings= training.getAll();
			
			training= training.getByNaamDao(searchTraining);
			
			for(int i=0;i<trainings.size();i++)
			{
				
				viewList.getItems().addAll(trainings.get(i).getTraining_ID()+": "+trainings.get(i).getTrainingNaam());
			
			}
		}
		
	
}
}
