package validator;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import data.Database;

@ManagedBean
public class LoginValidator {
	private String login;
	
	public void validateLoginName(FacesContext context, UIComponent component, Object  value) throws ValidatorException{
		login = (String) value;
		ResultSet rs = Database.executeQuery("SELECT Login FROM kunde WHERE Login = '"+ login +"'");
		
		try {
			if(!rs.next())
				throw new ValidatorException(new FacesMessage("Username existiert nicht"));
			
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public void validatePassword(FacesContext context, UIComponent component, Object  value) throws ValidatorException{
		ResultSet rs = Database.executeQuery("SELECT Login, Password FROM kunde WHERE Login = '"+ login +"' AND Password = '"+ (String) value +"'");
		
		try{
			if(!rs.next())
				throw new ValidatorException(new FacesMessage("Username und Passwort stimmen nicht \u00FCberein"));
		}catch(SQLException e){e.printStackTrace();}
	}
}
