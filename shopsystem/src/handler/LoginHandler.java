package handler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;

import data.Customer;
import data.Tools;

@ManagedBean
public class LoginHandler {
	
	@ManagedProperty(value="#{customer}")
	private Customer customer;
	
	public String signUpButton(){
		return "/register.xhtml";
	}
	
	public String loginButton(){
		HtmlInputText login = (HtmlInputText) Tools.dfsComponent("username");
		customer.setCustomerID((String) login.getValue());
		return "/mainpage.xhtml";
	}
	
	/**
	 * Needed to make injection (ManagedProperty) possible.
	 * @see <a href="http://www.nullpointer.at/2011/06/12/dependency-injection-managedbean-in-managedbean-einfuegen/">Janni Klick mich!</a>
	 */
	public void setCustomer(Customer bean){
		customer = bean;
	}
}
