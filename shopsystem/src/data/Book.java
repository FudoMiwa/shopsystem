package data;

import java.io.Serializable;

import database.Database;
import database.DBUtil;

public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String description;
	private String publisher;
	private String pictureURL;
	private double cost;
	private String title;
	private String category;
	private String author;
	private String isbn;
	private int quantity;
	
	public Book(String bookID){
		id = bookID;
		
		try{
			Database database = DBUtil.getStub();
			
			description = (String) database.getBookAttribute(bookID, "Beschreibung");
			publisher = (String) database.getBookAttribute(bookID, "Verlag");
			pictureURL = (String) database.getBookAttribute(bookID, "Bild");
			cost = (double) database.getBookAttribute(bookID, "Preis");
			title = (String) database.getBookAttribute(bookID, "Bezeichnung");
			category = (String) database.getBookAttribute(bookID, "Warengruppe");
			author = (String) database.getBookAttribute(bookID, "Autor");
			isbn = (String) database.getBookAttribute(bookID, "ISBN");
		}catch(Exception e){
			System.err.println("Book exception:");
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean equals(Object that){
		if(that instanceof Book )
			if(((Book) that).getID().equals(id))
				return true;
		
		return false;
	}
	
	/**
	 * @return the id
	 */
	public String getID() {
		return id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @return the pictureURL
	 */
	public String getPictureURL() {
		return pictureURL;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int amount){
		quantity = amount;
	}
}
