package handler;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

import data.CartElement;
import de.imut.ec.bshop.Bestellung;
import de.imut.ec.bshop.DBUtil;
import de.imut.ec.bshop.Kunde;
import de.imut.ec.bshop.Position;

@SessionScoped
@ManagedBean
public class CheckoutHandler {
	private String vorname, name, plz, ort, strasse, month, year, kartennr, zahlungsart;
	Kunde kunde;
	DBUtil db;
	
	public CheckoutHandler(){
		kunde = new Kunde();
		kunde.setGruppennr(7);
		db = new DBUtil();
	}
	
	public String orderButton(){
		//kunde.setGueltigbis(month + "/" + year);
		//db.persistKunde(kunde);
		return "/confirmation.xhtml";
	}
	
	public String finalOrderButton(List<CartElement> cartList){
		kunde.setGueltigbis(month + "/" + year);
		kunde.setZahlungsart(zahlungsart);
		db.persistKunde(kunde);
		
		Bestellung newOrder = new Bestellung(kunde.getId(), 7);
		int orderID = db.persistBestellung(newOrder);
		Position orderDetail;
		
		for(CartElement elem : cartList){
			orderDetail = new Position(orderID, elem.getBuch().getId(), elem.getQuantity(), 7);
			db.persistPosition(orderDetail);
		}		
		return "/end.xhtml";
	}
	
	public Kunde getKunde(){
		return kunde;
	}

	public void setMonth(String value){
		this.month = value;
	}
	
	public void setYear(String value){
		this.year = value;
	}
	
	public void setZahlungsart(String value){
		this.zahlungsart = value;
	}
	
	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		kunde.setVorname(vorname);
		this.vorname = vorname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		kunde.setName(name);
		this.name = name;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		kunde.setPlz(plz);
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		kunde.setOrt(ort);
		this.ort = ort;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		kunde.setStrasse(strasse);
		this.strasse = strasse;
	}

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}
	
	public String getZahlungsart() {
		return zahlungsart;
	}
	
	public String getKartennr() {
		return kartennr;
	}

	public void setKartennr(String kartennr) {
		kunde.setKartennr(kartennr);
		this.kartennr = kartennr;
	}
}
