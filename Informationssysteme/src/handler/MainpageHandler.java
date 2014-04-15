package handler;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import data.MyDBUtil;
import de.imut.ec.bshop.*;

import java.util.*;



@ManagedBean
@SessionScoped
public class MainpageHandler {	

	MyDBUtil db = new MyDBUtil();
	private List<String> category = db.findWarengruppen(7);
	
	private DataModel<Buch> buchModel;
	private List<Buch> list;
	private Buch aktuellesBuch;
	
	
	public String homeButton(){
		return "/mainpage.xhtml";
	}
	
	public String detailLink(){
		aktuellesBuch = buchModel.getRowData();
		return "/detailbook.xhtml";
	}
		
	public Buch getAktuellesBuch() {
		return aktuellesBuch;
	}
	
	public void setAktuellesBuch(Buch aktuellesBuch) {
		this.aktuellesBuch = aktuellesBuch;
	}
	
	public DataModel<Buch> getBooks(){
		return buchModel;
	}

	public String categoryLink(String selectedCategory){
		list = db.findBuecherByWarengruppe(selectedCategory);
		buchModel = new ListDataModel<Buch>(list);
		return "/mainpagecontent.xhtml";
	}
	
	public List<String> getCategories(){
		return category;
	}
	
	public Buch getBuch(){
		return aktuellesBuch;
	}
	
}