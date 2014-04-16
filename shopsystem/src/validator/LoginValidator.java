package validator;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.sun.rowset.*;

import database.DBUtil;
import database.Database;

@ManagedBean
public class LoginValidator {
	private String login;
	
	public void validateLoginName(FacesContext context, UIComponent component, Object  value) throws ValidatorException{
		login = (String) value;
		CachedRowSetImpl rs = null;
		
		try {
			rs = new CachedRowSetImpl();
			Database db = DBUtil.getStub();
			rs.populate(DBUtil.getStub().executeQuery("SELECT Login FROM kunde WHERE Login = '"+ login +"'"));
			
			if(!rs.next())
				throw new ValidatorException(new FacesMessage("Username existiert nicht"));
		} catch (Exception e) {
			System.err.println("LoginValidator exception:"+DBUtil.getStub());
			e.printStackTrace();
		} 
	}
	
	public void validatePassword(FacesContext context, UIComponent component, Object  value) throws ValidatorException{
		ResultSet rs = null;
		
		try {
			rs = DBUtil.getStub().executeQuery("SELECT Login, Password FROM kunde WHERE Login = '"+ login +"' AND Password = '"+ (String) value +"'");
			
			if(!rs.next())
				throw new ValidatorException(new FacesMessage("Username und Passwort stimmen nicht \u00FCberein"));
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}catch(SQLException e){e.printStackTrace();}
	}
}
