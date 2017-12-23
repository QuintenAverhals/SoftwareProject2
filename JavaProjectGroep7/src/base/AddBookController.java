package base;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;

import dao.TrainingBookDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AddBookController {
	public GridPane color;

	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	@FXML
	private TableView<Book> tableBooks;
	@FXML
	private TableColumn<Book, String> isbnCol;
	@FXML
	private TableColumn<Book, String> titleCol;
	@FXML
	private TableColumn<Book, String> authorCol;
	@FXML
	private TextField searchBookTextField;
	@FXML
	

	public void initialize() {

		String kleur = OptionsController.getColor();
		System.out.println(kleur);
		color.setStyle("-fx-background-color: #" + kleur);

	}

	public void addBookToTraining(ActionEvent event) {
		Book book = tableBooks.getSelectionModel().getSelectedItem();
		if (book != null) {
			System.out.println(book.getIsbn());
			TrainingBook obj = new TrainingBook(book.getIsbn(), TrainingOverviewController.TRAINING_ID);
			TrainingBookDAO.createTrainingBookLink(obj);
		}
		else {
			errorMsg("Error!","Please search for a book and select an item from the list.");
		}
	}


	public void searchForBook(ActionEvent event) {
		isbnCol.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> data) {
				return new SimpleStringProperty(data.getValue().getIsbn());
			}
		});
		titleCol.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> data) {
				return new SimpleStringProperty(data.getValue().getTitle());
			}
		});
		authorCol.setCellValueFactory(new Callback<CellDataFeatures<Book, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Book, String> data) {
				if (data.getValue().getAuthors().isEmpty()) {
					return new SimpleStringProperty("No author");
				}
				else {
					return new SimpleStringProperty(data.getValue().getAuthors().get(0));
				}
			}
		});

		tableBooks.setPlaceholder(new Label("No entry found."));
		String inputQuery = searchBookTextField.getText();

		Book book = searchByIsbn(inputQuery);
		ObservableList<Book> list;
		ArrayList<Book> books = searchByTitle(inputQuery);

		if (book != null) {
			ArrayList<Book> items = new ArrayList<Book>();
			items.add(book);
			list = FXCollections.observableArrayList(items);
			tableBooks.setItems(list);
		}


		else if (books != null) {
			list = FXCollections.observableArrayList(books);
			tableBooks.setItems(list);
		}
		else{
			tableBooks.getItems().clear();
		}

		//tableBooks.getSelectionModel().getSelectedItem();
	}

	public static Book searchByIsbn(String isbn) {

		String query = "isbn:" + isbn;
		Book book = new Book();

		try {
			Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, null)
					.setApplicationName("SoftwareProject2Groep7")
					.setGoogleClientRequestInitializer(new BooksRequestInitializer(Credentials.API_KEY_BOOKS)).build();
			List volumesList = books.volumes().list(query);
			Volumes volumes = volumesList.execute();
			if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
				//System.err.println("No books found for ISBN.");
				return null;
			}
			else {
				// Isbn
				book.setIsbn(isbn);
			}

			for (Volume volume : volumes.getItems()) {
				Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
				Volume.SaleInfo saleInfo = volume.getSaleInfo();

				// Title
				book.setTitle(volumeInfo.getTitle());

				// Author(s)
				java.util.List<String> authors = volumeInfo.getAuthors();

				if (authors != null && !authors.isEmpty()) {
					for (int i = 0; i < authors.size(); ++i) {
						book.addAuthor(authors.get(i));
					}
				}

				// Publisher
				book.setPublisher(volumeInfo.getPublisher());

				// Price | Ebook | buyLink
				if (saleInfo != null) {
					book.setIsEbook(saleInfo.getIsEbook());

					if ("FOR_SALE".equals(saleInfo.getSaleability())) {
						book.setPrice(saleInfo.getListPrice().getAmount());
						URL link = new URL(saleInfo.getBuyLink());
						book.setBuyLink(link);
					}
					else {
						book.setPrice(-1);
						book.setBuyLink(null);
					}
				}
			}
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return book;
	}

	public static ArrayList<Book> searchByTitle(String title) {
		String query = "intitle:" + title;
		ArrayList<Book> foundBooks = new ArrayList<Book>();
		boolean hasISBN = false;

		try {
			Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, null)
					.setApplicationName("SoftwareProject2Groep7")
					.setGoogleClientRequestInitializer(new BooksRequestInitializer(Credentials.API_KEY_BOOKS)).build();

			List volumesList = books.volumes().list(query).setMaxResults((long) 40);
			Volumes volumes = volumesList.execute();

			if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
				//System.err.println("No books found for Title.");
				return null;
			}

			for (Volume volume : volumes.getItems()) {
				Book book = new Book();
				Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
				Volume.SaleInfo saleInfo = volume.getSaleInfo();

				try {
					if ("ISBN_13".equals(volumeInfo.getIndustryIdentifiers().get(0).getType()) && volumeInfo.getIndustryIdentifiers().get(0).getType() != null) {
						book.setIsbn(volumeInfo.getIndustryIdentifiers().get(0).getIdentifier());
						hasISBN = true;
					}
					else {
						hasISBN = false;
						book.setIsbn("No ISBN");
					}
				}
				catch (NullPointerException ex) {
					book.setIsbn("No ISBN");
					hasISBN = false;
				}



				//volumeInfo.getIndustryIdentifiers().get(0);


				// Title
				book.setTitle(volumeInfo.getTitle());

				// Author(s)
				java.util.List<String> authors = volumeInfo.getAuthors();

				if (authors != null && !authors.isEmpty()) {
					for (int i = 0; i < authors.size(); ++i) {
						book.addAuthor(authors.get(i));
					}
				}

				// Publisher
				book.setPublisher(volumeInfo.getPublisher());

				// Price | Ebook | buyLink
				if (saleInfo != null) {
					book.setIsEbook(saleInfo.getIsEbook());

					if ("FOR_SALE".equals(saleInfo.getSaleability())) {
						book.setPrice(saleInfo.getListPrice().getAmount());
						URL link = new URL(saleInfo.getBuyLink());
						book.setBuyLink(link);
					}
					else {
						book.setPrice(-1);
						book.setBuyLink(null);
					}
				}
				if (hasISBN) {
					foundBooks.add(book);
				}
			}
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return foundBooks;
	}

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

	public void logout(ActionEvent event) throws Exception
	{

		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/loginMenu.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);

		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);

		window.show();
	}
	public void goBack(ActionEvent event) throws Exception
	{
		Parent passwordForgottenParent = FXMLLoader.load(getClass().getResource("../gui/trainingOverview.fxml"));
		Scene passwordForgottenScene = new Scene(passwordForgottenParent);

		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(passwordForgottenScene);

		window.show();
	}
	public static void errorMsg(String title, String msg)
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
