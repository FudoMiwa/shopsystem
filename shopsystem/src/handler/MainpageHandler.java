package handler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import data.Book;
import data.Database;
import data.ShoppingCart;

@ManagedBean
@SessionScoped
public class MainpageHandler {	
	private DataModel<Book> bookModel;
	
	@ManagedProperty(value="#{shoppingCart}")
	private ShoppingCart shoppingCart;
	
	public String homeButton(){
		return "/mainpage.xhtml";
	}
	
	public String shoppingCartButton(){
		return "/shoppingcart.xhtml";
	}
	
	public String categoryLink(String selectedCategory){
		bookModel = new ListDataModel<Book>(Database.getBooks(selectedCategory));
		return "/mainpagecontent.xhtml";
	}
	
	public DataModel<Book> getBooks(){
		return bookModel;
	}
	
	public void addToCartButton(){
		shoppingCart.addBook(bookModel.getRowData());
	}
	
	public void setShoppingCart(ShoppingCart bean){
		shoppingCart = bean;
	}
	
	public int getTotalCartSize(){
		return shoppingCart.getTotalSize();
	}
}