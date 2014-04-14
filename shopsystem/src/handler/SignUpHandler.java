package handler;

import java.rmi.RemoteException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlSelectOneMenu;

import data.Customer;
import data.Tools;
import database.DatabaseClient;

/**
 * Handler for signup-window.
 */
@ManagedBean
public class SignUpHandler {
	
	@ManagedProperty(value="#{customer}")
	private Customer customer;
	
	/**
	 * ActionListener for acceptButton.
	 * @return Link to mainpage
	 */
	public String acceptButton(){
		registerNewCustomer();
		
		HtmlInputText login = (HtmlInputText) Tools.dfsComponent("loginName");
		customer.setCustomerID((String) login.getValue());
		
		return "/mainpage.xhtml";
	}
	
	private void registerNewCustomer(){
		String[] customerData = new String[10];
		
		customerData[0] = (String) ((HtmlInputText) Tools.dfsComponent("loginName")).getValue();
		customerData[1] = (String) ((HtmlInputSecret) Tools.dfsComponent("password")).getValue();
		customerData[2] = (String) ((HtmlInputText) Tools.dfsComponent("firstName")).getValue();
		customerData[3] = (String) ((HtmlInputText) Tools.dfsComponent("lastName")).getValue();
		customerData[4] = (String) ((HtmlInputText) Tools.dfsComponent("postcode")).getValue();
		customerData[5] = (String) ((HtmlInputText) Tools.dfsComponent("city")).getValue();
		customerData[6] = (String) ((HtmlInputText) Tools.dfsComponent("street")).getValue();
		customerData[7] = "creditCard";
		customerData[8] = (String) ((HtmlInputText) Tools.dfsComponent("creditCardNumber")).getValue();
		
		String validThrough = (String) ((HtmlSelectOneMenu) Tools.dfsComponent("months")).getValue();
		validThrough += ".";
		validThrough += (String) ((HtmlSelectOneMenu) Tools.dfsComponent("years")).getValue();
		customerData[9] = validThrough;
		
		try {
			DatabaseClient.getStub().recordNewCustomer(customerData);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Needed to make injection (ManagedProperty) possible.
	 * @see <a href="http://www.nullpointer.at/2011/06/12/dependency-injection-managedbean-in-managedbean-einfuegen/">Janni Klick mich!</a>
	 */
	public void setCustomer(Customer bean){
		customer = bean;
	}
}
