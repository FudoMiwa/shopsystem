package data;

public class Book {
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
		description = (String) Database.getBookAttribute(bookID, "Beschreibung");
		publisher = (String) Database.getBookAttribute(bookID, "Verlag");
		pictureURL = (String) Database.getBookAttribute(bookID, "Bild");
		cost = (double) Database.getBookAttribute(bookID, "Preis");
		title = (String) Database.getBookAttribute(bookID, "Bezeichnung");
		category = (String) Database.getBookAttribute(bookID, "Warengruppe");
		author = (String) Database.getBookAttribute(bookID, "Autor");
		isbn = (String) Database.getBookAttribute(bookID, "ISBN");
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
