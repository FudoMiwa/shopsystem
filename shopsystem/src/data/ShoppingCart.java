package data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ShoppingCart implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<CartElement> books = new LinkedList<>();
	private double totalCost;
	private int totalSize; //Amount of all items in shopping-cart
	
	public void addBook(Book book){
		CartElement element = findElement(book);
		
		totalSize += book.getQuantity();
		totalCost += book.getCost() * book.getQuantity();
		
		if(element != null)
			element.setQuantity(element.getQuantity() + book.getQuantity());
		else
			books.add(new CartElement(book));
	}
	
	public void removeBook(Book book){
		updateBookAmount(book, 0);
	}
	
	public void updateBookAmount(Book book, int newQuantity){
		totalSize -= book.getQuantity();
		totalCost -= book.getQuantity() * book.getCost();
		
		if(newQuantity == 0)
			books.remove(findElement(book));
		else{
			book.setQuantity(newQuantity);
			totalCost += newQuantity * book.getCost();
			totalSize += newQuantity;
		}
	}
	
	private CartElement findElement(Book book){
		
		for(CartElement element : books)
			if(element.getBook().equals(book))
				return element;
		
		return null;
	}
	
	public double getTotalCost(){
		return totalCost;
	}
	
	public List<CartElement> getCartContent(){
		return books;
	}
	
	public ShoppingCart getShoppingCart(){
		return this;
	}
	
	public int getTotalSize(){
		return totalSize;
	}
}