package handler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import data.CartElement;
import data.ShoppingCart;

@ManagedBean
@SessionScoped
public class ShoppingCartHandler {
	
	@ManagedProperty(value="#{shoppingCart}")
	private ShoppingCart shoppingCart;
	
	private DataModel<CartElement> cartContent;
	
	public String checkoutButton(){
		return "/checkout.xhtml";
	}
	
	public void deleteItemLink(){
		CartElement elem = cartContent.getRowData();
		shoppingCart.removeBook(elem.getBook());
	}
	
	public void updateLink(){
		CartElement elem = cartContent.getRowData();
		shoppingCart.updateBookAmount(elem.getBook(), elem.getQuantity());
	}
	
	public DataModel<CartElement> getCartContent(){
		cartContent = new ListDataModel<>(shoppingCart.getCartContent());
		return cartContent;
	}
	
	public void setShoppingCart(ShoppingCart cart){
		this.shoppingCart = cart;
	}
	
	/**
	 * @return TotalCost of all items in shopping-cart
	 */
	public double getTotalCost(){
		return shoppingCart.getTotalCost();
	}
}
