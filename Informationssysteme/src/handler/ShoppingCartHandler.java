package handler;

import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import data.CartElement;
import de.imut.ec.bshop.Buch;
import de.imut.ec.bshop.DBUtil;


@ManagedBean
@SessionScoped
public class ShoppingCartHandler {
	
	DBUtil db = new DBUtil();
	
	private List<CartElement> list = new LinkedList<>();
	private DataModel<CartElement> cartContent = new ListDataModel<CartElement>(list);
	
	private int quantityToBuy;
	
	
	//Regelt das Inputfeld der Detailseite und packt Buch in den Warenkorb
	public void addToCartButton(int id){
		Buch buch = db.findBuchById(id);
		CartElement newElement = new CartElement(buch, quantityToBuy);

		if(list.contains(newElement)){
			CartElement currentElement = list.get(list.indexOf(newElement));
			currentElement.setQuantity(currentElement.getQuantity() + quantityToBuy);			
		}else{
			list.add(newElement);
		}		
		quantityToBuy = 1;
	}
	
	//Loescht Buch aus Warenkorb
	public void deleteItemLink(){
		list.remove(cartContent.getRowData());
	}
	
	public void updateLink(){
		CartElement tmpElement = cartContent.getRowData();
		if(tmpElement.getQuantity() == 0){
			list.remove(tmpElement);
		}
	}
	
	public String checkoutButton(){
		return "/checkout.xhtml";
	}
	
	public String shoppingCartButton(){
		return "/shoppingCart.xhtml";
	}
	
	public DataModel<CartElement> getCartContent(){
		return cartContent;
	}
	
	public List<CartElement> getCartList(){
		return list;
	}
	
	public void setQuantity(int quantity){
		this.quantityToBuy = quantity;
	}
	
	public int getQuantity(){
		return quantityToBuy;
	}
	
	public double getTotalCost(){
		double totalCost = 0;
		for(CartElement elem : list){
			totalCost += elem.getCost();
		}
		return totalCost;
	}
	
	public int getTotalQuantity(){
		int totalQuantity = 0;
		for(CartElement elem : list){
			totalQuantity += elem.getQuantity();
		}
		return totalQuantity;
	}
}