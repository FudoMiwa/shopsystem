package validator;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import database.DatabaseClient;


/**
 * Handles validation for the signup-window.
 */
@ManagedBean
public class SignUpValidator {
	
	/**
	 * Throws exception if 1. Login shorter than 5 character  2. Login longer than 20 character 3. Login already in database.
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validateLoginName(FacesContext context, UIComponent component, Object  value) throws ValidatorException{
		String input = (String) value;
		ResultSet rs = null;
		
		try {
			rs = DatabaseClient.getStub().executeQuery("SELECT Login FROM kunde WHERE Login = '"+ input +"'");
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		if(input.length() < 5)
			throw new ValidatorException(new FacesMessage("Loginname muss mindestens 5 Zeichen enthalten"));
		else if(input.length() > 20)
			throw new ValidatorException(new FacesMessage("Loginname darf maximal 20 Zeichen enthalten"));
		else
			try{
				if(rs.next())
					throw new ValidatorException(new FacesMessage("Loginname schon vorhanden"));
			}catch(SQLException e){ e.printStackTrace();}
	}
	
	/**
	 * Throws exception if 1. Login shorter than 5 character  2. Login longer than 20 character.
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 */
	public void validatePassword(FacesContext context, UIComponent component, Object  value) throws ValidatorException{
		String input = (String) value;
		
		if(input.length() < 5)
			throw new ValidatorException(new FacesMessage("Passwort muss mindestens 5 Zeichen enthalten"));
		else if(input.length() > 20)
			throw new ValidatorException(new FacesMessage("Passwort darf maximal 20 Zeichen enthalten"));
	}
	
}
