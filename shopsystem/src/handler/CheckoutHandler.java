package handler;

import java.rmi.RemoteException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import data.Customer;
import data.ShoppingCart;
import database.DatabaseClient;

@SessionScoped
@ManagedBean
public class CheckoutHandler {
	
	@ManagedProperty(value="#{shoppingCart}")
	private ShoppingCart shoppingCart;
	
	@ManagedProperty(value="#{customer}")
	private Customer customer;
	
	public String orderButton(){
		try {
			DatabaseClient.getStub().recordNewOrder(customer, shoppingCart);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return "/end.xhtml";
	}
	
	public void setCustomer(Customer bean){
		customer = bean;
	}
	
	public void setShoppingCart(ShoppingCart bean){
		shoppingCart = bean;
	}
}
