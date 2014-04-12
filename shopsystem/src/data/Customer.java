package data;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class Customer {
	private String customerID;
	private String validThrough;
	
	/**
	 * Needed to make injection (ManagedProperty) possible.
	 * @see <a href="http://www.nullpointer.at/2011/06/12/dependency-injection-managedbean-in-managedbean-einfuegen/">Janni Klick mich!</a>
	 * @return
	 */
	public Customer getCustomer(){
		return this;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String loginName) {
		customerID = Database.getCustomerID(loginName);
	}
	
	public void setLastName(String value){
		Database.setCustomerAttribute(customerID, "Nachname", value);
	}
	
	public void setPostcode(String value){
		Database.setCustomerAttribute(customerID, "PLZ", value);
	}
	
	public void setCity(String value){
		Database.setCustomerAttribute(customerID, "Ort", value);
	}
	
	public void setStreet(String value){
		Database.setCustomerAttribute(customerID, "Strasse", value);
	}
	
	public void setCreditCardNumber(String value){
		Database.setCustomerAttribute(customerID, "`Karten-Nr.`", value);
	}
	
	public void setValidThroughMonth(String value){
		validThrough = value +".";
	}
	
	public void setValidThroughYear(String value){
		validThrough += value;
		Database.setCustomerAttribute(customerID, "Gueltig_bis", validThrough);
	}
	
	public void setFirstName(String value){
		Database.setCustomerAttribute(customerID, "Vorname", value);
	}
	
	public String getLoginName(){
		return Database.getCustomerAttribute(customerID, "Login");
	}
	
	public String getPassword(){
		return Database.getCustomerAttribute(customerID, "Password");
	}
	
	public String getFirstName(){
		return Database.getCustomerAttribute(customerID, "Vorname");
	}
	
	public String getLastName(){
		return Database.getCustomerAttribute(customerID, "Name");
	}
	
	public String getPostcode(){
		return Database.getCustomerAttribute(customerID, "PLZ");
	}
	
	public String getCity(){
		return Database.getCustomerAttribute(customerID, "Ort");
	}
	
	public String getStreet(){
		return Database.getCustomerAttribute(customerID, "Strasse");
	}
	
	public String getCreditCardNumber(){
		return Database.getCustomerAttribute(customerID, "`Karten-Nr.`");
	}
	
	private String getValidThrough(){
		return Database.getCustomerAttribute(customerID, "Gueltig_bis");
	}
	
	public String getValidThroughMonth(){
		String date = getValidThrough();
		int index = date.indexOf('.');
		return date.substring(0, index);
	}
	
	public String getValidThroughYear(){
		String date = getValidThrough();
		int index = date.indexOf('.');
		return date.substring(index+1);
	}
}
