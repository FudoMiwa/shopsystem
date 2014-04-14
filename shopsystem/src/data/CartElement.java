package data;

import java.io.Serializable;

public class CartElement implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	Book book;
	int quantity;
	
	public CartElement(Book book){
		this.book = book;
		this.quantity = book.getQuantity();
	}
	
	public Book getBook(){
		return book;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int amount){
		quantity = amount;
	}
	
	public boolean equals(Object that){
		if(that instanceof CartElement && book.equals(((CartElement) that).getBook()))
			return true;
		
		return false;
	}
}