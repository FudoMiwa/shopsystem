package data;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import database.DBUtil;

@SessionScoped
@ManagedBean
public class Customer implements Serializable{
	private static final long serialVersionUID = 1L;
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
		try {
			customerID = DBUtil.getStub().getCustomerID(loginName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setLastName(String value){
		try {
			DBUtil.getStub().setCustomerAttribute(customerID, "Name", value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setPostcode(String value){
		try {
			DBUtil.getStub().setCustomerAttribute(customerID, "PLZ", value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setCity(String value){
		try {
			DBUtil.getStub().setCustomerAttribute(customerID, "Ort", value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setStreet(String value){
		try {
			DBUtil.getStub().setCustomerAttribute(customerID, "Strasse", value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setCreditCardNumber(String value){
		try {
			DBUtil.getStub().setCustomerAttribute(customerID, "`Karten-Nr.`", value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setValidThroughMonth(String value){
		validThrough = value +".";
	}
	
	public void setValidThroughYear(String value){
		validThrough += value;
		try {
			DBUtil.getStub().setCustomerAttribute(customerID, "Gueltig_bis", validThrough);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setFirstName(String value){
		try {
			DBUtil.getStub().setCustomerAttribute(customerID, "Vorname", value);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public String getLoginName(){
		try {
			return DBUtil.getStub().getCustomerAttribute(customerID, "Login");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getPassword(){
		try {
			return DBUtil.getStub().getCustomerAttribute(customerID, "Password");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getFirstName(){
		try {
			return DBUtil.getStub().getCustomerAttribute(customerID, "Vorname");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getLastName(){
		try {
			return DBUtil.getStub().getCustomerAttribute(customerID, "Name");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getPostcode(){
		try {
			return DBUtil.getStub().getCustomerAttribute(customerID, "PLZ");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getCity(){
		try {
			return DBUtil.getStub().getCustomerAttribute(customerID, "Ort");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getStreet(){
		try {
			return DBUtil.getStub().getCustomerAttribute(customerID, "Strasse");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getCreditCardNumber(){
		try {
			return DBUtil.getStub().getCustomerAttribute(customerID, "`Karten-Nr.`");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String getValidThrough(){
		try {
			return DBUtil.getStub().getCustomerAttribute(customerID, "Gueltig_bis");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return null;
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
