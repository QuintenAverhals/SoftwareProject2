package base;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Book {
	private String isbn;
	private List<String> authors;
	private String publisher;
	private String title;
	private boolean isEbook;
	private double price;
	private URL buyLink;
	
	public Book() {
		super();
		this.authors = new ArrayList<String>();
	}
	
	
	public Book(String isbn, List<String> authors, String publisher, String title, boolean isEbook, double price, URL buyLink) {
		super();
		this.isbn = isbn;
		this.authors = authors;
		this.publisher = publisher;
		this.title = title;
		this.isEbook = isEbook;
		this.price = price;
		this.buyLink = buyLink;
	}

	public void addAuthor(String author) {
		authors.add(author);
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean getIsEbook() {
		return this.isEbook;
	}
	public void setIsEbook(boolean isEbook) {
		this.isEbook = isEbook;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public URL getBuyLink() {
		return buyLink;
	}
	public void setBuyLink(URL buyLink) {
		this.buyLink = buyLink;
	}
}
