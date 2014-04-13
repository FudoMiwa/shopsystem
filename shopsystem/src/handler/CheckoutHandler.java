package handler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import data.Customer;
import data.Database;
import data.ShoppingCart;

@SessionScoped
@ManagedBean
public class CheckoutHandler {
	
	@ManagedProperty(value="#{shoppingCart}")
	private ShoppingCart shoppingCart;
	
	@ManagedProperty(value="#{customer}")
	private Customer customer;
	
	public String orderButton(){
		Database.recordNewOrder(customer, shoppingCart);
		return "/end.xhtml";
	}
	
	public String confirmationButton(){
		return "/confirmation.xhtml";
	}
	
	
	public void setCustomer(Customer bean){
		customer = bean;
	}
	
	public void setShoppingCart(ShoppingCart bean){
		shoppingCart = bean;
	}
}
