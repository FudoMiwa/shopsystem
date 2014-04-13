package validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
public class CheckoutValidator {

	//Erlaubt nur Ziffern [0-9] - maximal 5-stellig
	public void validatePLZ(FacesContext context, UIComponent component, Object value) throws ValidatorException{
		if(!((String) value).matches("\\d{5}")){
			throw new ValidatorException(new FacesMessage("Bitte geben Sie eine gültige Postleitzahl ein"));
		}
	}
		
	//Prueft richtige Kreditkartennummer
	public void validateKreditkarte(FacesContext context, UIComponent component, Object value) throws ValidatorException{
		if(!((String) value).matches("\\d{16}")){
			throw new ValidatorException(new FacesMessage("Fehlerhafte Kreditkartennummer: Ihre Kreditkartennummer muss 16-stellig sein, keine Sonderzeichen erlaubt!"));
		}
	}
	
	/*
	//Prueft auf Buchstaben - keine Ziffern erlaubt
	public void validateNames(FacesContext context, UIComponent component, Object value) throws ValidatorException{
		if(!((String) value).matches("\\D")){
			throw new ValidatorException(new FacesMessage("Fehlerhafte Eingabe"));
		}
	}*/
}
